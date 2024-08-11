package com.curso.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.ecommerce.model.Almacen;
import com.curso.ecommerce.model.Cliente;
import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Empleado;
import com.curso.ecommerce.model.Inventario;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Pedido;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Proveedor;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.model.Venta;
import com.curso.ecommerce.service.AlmacenService;
import com.curso.ecommerce.service.ClienteService;
import com.curso.ecommerce.service.EmpleadoService;
import com.curso.ecommerce.service.IDetalleOrdenService;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.InventarioService;
import com.curso.ecommerce.service.PedidoService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.ProveedorService;
import com.curso.ecommerce.service.VentaService;



@Controller
@RequestMapping("/")
public class HomeController {
	//****************************************************
	@Autowired	
	//private AlmacenDao AlmacenDao;
	private AlmacenService almacenService;
	@Autowired	
	//private ClienteDao ClienteDao;
	private ClienteService clienteService;
	@Autowired	
	//private EmpleadoDao EmpleadoDao;
	private EmpleadoService empleadoService;
	@Autowired	
	//private InventarioDao InventarioDao;
	private InventarioService inventarioService;
	@Autowired	
	//private PedidoDao PedidoDao;
	private PedidoService pedidoService;
	@Autowired	
	//private ProveedorDao ProveedorDao;
	private ProveedorService proveedorService;
	@Autowired	
	//private VentaDao VentaDao;
	private VentaService ventaService;
	//*************************************************************
	
	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@Autowired
	private IOrdenService ordenService;
	
	@Autowired
	private IDetalleOrdenService detalleOrdenService;

	// para almacenar los detalles de la orden
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden
	Orden orden = new Orden();

	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
		
		model.addAttribute("productos", productoService.findAll());
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";
	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto);

		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "usuario/resumenorden";
	}
	
	// guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session ) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		
		//usuario
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
		
		orden.setUsuario(usuario);
		ordenService.save(orden);
		
		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}
		
		///limpiar lista y orden
		orden = new Orden();
		detalles.clear();
		
		return "redirect:/";
	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, Model model) {
		log.info("Nombre del producto: {}", nombre);
		List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
		model.addAttribute("productos", productos);		
		return "usuario/home";
	}


//*****************************


//////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/almacen")
public String mostrarA(Model model) {
//Iterable<Almacen> almacenes = AlmacenDao.findAll();
List<Almacen> almacenes = almacenService.listarAlmacen();
model.addAttribute("almacenes", almacenes);
return "administrador/almacen";
}
@GetMapping("/agregarA")
public String agregarA(Almacen almacen) {
return "administrador/modificarA";
}
@PostMapping("/guardarA")
public String guardarA(@Valid Almacen almacen, BindingResult bindingResult) {
if(bindingResult.hasErrors()){
return "administrador/modificarA";
}
almacenService.guardar(almacen);
return "redirect:administrador/almacen";
}
@GetMapping("/editarA/{id}")
public String editarA(Almacen almacen, Model model) {
almacen = almacenService.encontrarAlmacen(almacen);
model.addAttribute("almacen", almacen);
return "administrador/modificarA";
}
@GetMapping("/eliminarA/{id}")
public String eliminarA(Almacen almacen) {
almacenService.eliminar(almacen);
return "redirect:/administrador/almacen";
}
//////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/cliente")
public String mostrarC(Model model) {
//Iterable<Cliente> clientes = ClienteDao.findAll();
List<Cliente> clientes = clienteService.listarCliente();
model.addAttribute("clientes", clientes);
return "administrador/cliente";
}
@GetMapping("/agregar")
public String agregar(Cliente cliente) {
return "administrador/modificar";
}
@PostMapping("/guardar")
public String guardar(@Valid Cliente cliente, Errors errores) {
if(errores.hasErrors()){
return "administrador/modificar";
}
clienteService.guardar(cliente);
return "redirect:administrador/cliente";
}
@GetMapping("/editar/{id}")
public String editar(Cliente cliente, Model model) {
cliente = clienteService.encontrarCliente(cliente);
model.addAttribute("cliente", cliente);
return "administrador/modificar";
}
@GetMapping("/eliminar/{id}")
public String eliminar(Cliente cliente) {
clienteService.eliminar(cliente);
return "redirect:/administrador/cliente";
}
/////////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/empleado")
public String mostrarE(Model model) {
//Iterable<Empleado> empleados = EmpleadoDao.findAll();
List<Empleado> empleados = empleadoService.listarEmpleado();
model.addAttribute("empleados", empleados);
return "administrador/empleado";
}
@GetMapping("/agregarE")
public String agregarE(Empleado empleado) {
return "administrador/modificarE";
}
@PostMapping("/guardarE")
public String guardarE(@Valid Empleado empleado, Errors errores) {
if(errores.hasErrors()){
return "administrador/modificarE";
}
empleadoService.guardar(empleado);
return "redirect:administrador/empleado";
}
@GetMapping("/editarE/{id}")
public String editarE(Empleado empleado, Model model) {
empleado = empleadoService.encontrarEmpleado(empleado);
model.addAttribute("empleado", empleado);
return "administrador/modificarE";
}
@GetMapping("/eliminarE/{id}")
public String eliminarE(Empleado empleado) {
empleadoService.eliminar(empleado);
return "redirect:/administrador/empleado";
}
/////////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/inventario")
public String mostrarI(Model model) {
//Iterable<Inventario> inventarios = InventarioDao.findAll();
List<Inventario> inventarios = inventarioService.listarInventario();
model.addAttribute("inventarios", inventarios);
return "administrador/inventario";
}
@GetMapping("/agregarI")
public String agregarI(Inventario inventario) {
return "administrador/modificarI";
}
@PostMapping("/guardarI")
public String guardarI(@Valid Inventario inventario, Errors errores) {
if(errores.hasErrors()){
return "administrador/modificarI";
}
inventarioService.guardar(inventario);
return "redirect:administrador/inventario";
}
@GetMapping("/editarI/{id}")
public String editarI(Inventario inventario, Model model) {
inventario = inventarioService.encontrarInventario(inventario);
model.addAttribute("inventario", inventario);
return "administrador/modificarI";
}
@GetMapping("/eliminarI/{id}")
public String eliminarI(Inventario inventario) {
inventarioService.eliminar(inventario);
return "redirect:/administrador/inventario";
}
/////////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/pedido")
public String mostrarP(Model model) {
//Iterable<Pedido> pedidos = PedidoDao.findAll();
List<Pedido> pedidos = pedidoService.listarPedido();
model.addAttribute("pedidos", pedidos);
return "administrador/pedido";
}
@GetMapping("/agregarPed")
public String agregarPed(Pedido pedido) {
return "administrador/modificarPed";
}
@PostMapping("/guardarPed")
public String guardarPed(@Valid Pedido pedido, Errors errores) {
if(errores.hasErrors()){
return "administrador/modificarPed";
}
pedidoService.guardar(pedido);
return "redirect:administrador/pedido";
}
@GetMapping("/editarPed/{id}")
public String editarPed(Pedido pedido, Model model) {
pedido = pedidoService.encontrarPedido(pedido);
model.addAttribute("pedido", pedido);
return "administrador/modificarPed";
}
@GetMapping("/eliminarPed/{id}")
public String eliminarPed(Pedido pedido) {
pedidoService.eliminar(pedido);
return "redirect:/administrador/pedido";
}
/////////////////////////////////////////////////////////////////////////////////////////
@GetMapping("administrador/proveedor")
public String mostrarPr(Model model) {
//Iterable<Proveedor> proveedores = ProveedorDao.findAll();
List<Proveedor> proveedores = proveedorService.listarProveedor();
model.addAttribute("proveedores", proveedores);
return "administrador/proveedor";
}
@GetMapping("/agregarProv")
public String agregarProv(Proveedor proveedor) {
return "administrador/modificarProv";
}
@PostMapping("/guardarProv")
public String guardarProv(@Valid Proveedor proveedor, Errors errores) {
if(errores.hasErrors()){
return "administrador/modificarProv";
}
proveedorService.guardar(proveedor);
return "redirect:administrador/proveedor";
}
@GetMapping("/editarProv/{id}")
public String editarProv(Proveedor proveedor, Model model) {
proveedor = proveedorService.encontrarProveedor(proveedor);
model.addAttribute("proveedor", proveedor);
return "administrador/modificarProv";
}
@GetMapping("/eliminarProv/{id}")
public String eliminarProv(Proveedor proveedor) {
proveedorService.eliminar(proveedor);
return "redirect:/administrador/proveedor";
}
////////////////////////////////////////////////////////////////////////////////////////
@GetMapping("/venta")
public String mostrarV(Model model) {
//Iterable<Venta> ventas = VentaDao.findAll();
List<Venta> ventas = ventaService.listarVenta();
model.addAttribute("ventas", ventas);
return "venta";
}
@GetMapping("/agregarV")
public String agregarV(Venta venta) {
return "modificarV";
}
@PostMapping("/guardarV")
public String guardarV(@Valid Venta venta, Errors errores) {
if(errores.hasErrors()){
return "modificarV";
}
ventaService.guardar(venta);
return "redirect:/venta";
}
@GetMapping("/editarV/{id}")
public String editarV(Venta venta, Model model) {
venta = ventaService.encontrarVenta(venta);
model.addAttribute("venta", venta);
return "modificarV";
}
@GetMapping("/eliminarV/{id}")
public String eliminarV(Venta venta) {
ventaService.eliminar(venta);
return "redirect:/venta";
}

}
