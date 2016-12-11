package com.developer.celulasdepaz.modelo;

import com.developer.celulasdepaz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Glosario {
    public static final List<Glosario> Eventos = new ArrayList<Glosario>();
    public static final List<Glosario> GlosarioA = new ArrayList<>();
    public static final List<Glosario> GlosarioB = new ArrayList<>();
    public static final List<Glosario> GlosarioC = new ArrayList<>();
    public static final List<Glosario> Hacer = new ArrayList<>();
    public static final List<Glosario> Glosarioe = new ArrayList<>();
    public static final List<Glosario> Glosariof = new ArrayList<>();
    public static final List<Glosario> Glosariog = new ArrayList<>();
    public static final List<Glosario> Glosarioh = new ArrayList<>();
    public static final List<Glosario> Glosarioi = new ArrayList<>();
    public static final List<Glosario> Glosarioj = new ArrayList<>();
    public static final List<Glosario> Glosariol = new ArrayList<>();
    public static final List<Glosario> Glosariom = new ArrayList<>();
    public static final List<Glosario> Glosarion = new ArrayList<>();
    public static final List<Glosario> GlosarioO = new ArrayList<>();
    public static final List<Glosario> Glosariop = new ArrayList<>();
    public static final List<Glosario> Glosarior = new ArrayList<>();
    public static final List<Glosario> Glosarios = new ArrayList<>();
    public static final List<Glosario> Glosariot = new ArrayList<>();

    static {

        Eventos.add(new Glosario("Plocs", "GGG", R.drawable.banco));
        //Glosario A
        GlosarioA.add(new Glosario("Abono en cuenta", "Ingreso o anotación de una cantidad de dinero en la cuenta bancaria, de forma que aumenta el saldo de la misma", R.drawable.abonocuenta));
        GlosarioA.add(new Glosario("Ahorro", "Es la parte de los ingresos que es usado para atender necesidades futuras", R.drawable.guardar));
        GlosarioA.add(new Glosario("Aguinaldo", "Beneficio económico que las empresas e instituciones entregan a sus trabajadores", R.drawable.aguinaldo));
        GlosarioA.add(new Glosario("Anualidad", " Es una sucesion de pagos generalmente del mismo monto que se realizan a intervalos de tiempo iguales y con el interes compuesto.", R.drawable.tarjeta_credito));

        // Glosario B
        GlosarioB.add(new Glosario("Banca en línea", "Toda banca operativa que utiliza internet para que sus clientes realicen diferentes " +
                "operaciones sin necesidad de desplazarse a la oficina bancaria", R.drawable.logodinero));
        GlosarioB.add(new Glosario("Banco", "Empresa comercial que realiza operaciones financieras con el dinero procedente de accionistas y clientes", R.drawable.banco));
        GlosarioB.add(new Glosario("Buró de crédito", "Instituciones que reciben, procesan y administran información sobre el comportamiento  o historial de crediticio de los usuarrios ", R.drawable.credito));

        //Glosario con C

        GlosarioC.add(new Glosario("Cobertura", "Tipo de riesgo y monto que se compromete a cubrir una compañia de seguros", R.drawable.cobertura));
        GlosarioC.add(new Glosario("Crédito", "Contrato por lo cual una entidad financiera pone a disposición del cliente cierta cantidad de dinero", R.drawable.creditoauto));
        GlosarioC.add(new Glosario("Cuenta Habiente", "Persona que tiene un contrato vigente con una institución para que le maneje una cuenta", R.drawable.cuentahabiente));
        GlosarioC.add(new Glosario("Ciclo de facturación", "El número de días trasncurridos entre el ultimo estado de cuenta y la fecha del estado de cuenta actual", R.drawable.ciclofacturacion));

        //Glosario con D
        Hacer.add(new Glosario("Deducible", "Es la cantidad que debe aportar el asegurado en caso de ocurrir el siniestro cubierto en su seguro", R.drawable.deducible));
        Hacer.add(new Glosario("Deuda", "Obligación que alguien tiene de pagar satisfactoriamente o reintegrar a otra persona", R.drawable.deuda));
        Hacer.add(new Glosario("Divisa", "Monedas o billetes comúnente aceptados para realizar transacciones comerciales en el mercado internacional", R.drawable.divisas));
        //Hacer.add(new Glosario("","",R.drawable.tarjeta));

        //Glosario con E

        Glosarioe.add(new Glosario("Estado de cuenta", "Reporte donde se registran todas las transacciones que el titular ha efectuado entre la fecha de emisión y corte", R.drawable.estadocuenta));
        // Glosarioe.add(new Glosario("","",R.drawable.tarjeta));
        //Glosarioe.add(new Glosario("","",R.drawable.tarjeta));


        // Glosario con F
        Glosariof.add(new Glosario("Fecha de corte", "Día establecido como limite para registrar las transacciones que se realizan durante un periodo de tiempo", R.drawable.fechacorte));
        Glosariof.add(new Glosario("Fecha de pago", "Fecha estalbecida por la emisora de la tarjeta para recibir los pagos  ", R.drawable.fechapago));

        //Glosario G
        Glosariog.add(new Glosario("Gasto", "Es el dinero destinado de una persona o familia con el fin de obtener bienes o servivios", R.drawable.gasto));

        //Glosario H
        Glosarioh.add(new Glosario("Hipoteca", "Garantía que recae sobre bienes inmuebles", R.drawable.hipoteca));

        //Glosario I
        Glosarioi.add(new Glosario("Interés", "Suma de dinero que cobra una institución financiera por el servicio de un crédito", R.drawable.interes));
        // Glosarioi.add(new Glosario("Ingreso","Cantidad de dinero entregado en una cuenta bancaria",R.drawable.tarjeta_credito));


        //Glosario L
        Glosariol.add(new Glosario("Línea de crediro", "Convenio entre la institución financiera y el cliente con respecto a la cantidad de crédito que se le proporcionará ", R.drawable.lineacredito));


        //Glosario M

        Glosariom.add(new Glosario("Monto", "Cantidad de dinero prestada", R.drawable.monto));


        //Glosario O
        GlosarioO.add(new Glosario("Operación financiera", "Negociación o contrato entre dos o más personas para la compra o venta de productos financieros", R.drawable.operacionfinancieraai));

        //Glosario P
        Glosariop.add(new Glosario("Pago mínimo", "Es el valor mínimo que debe ser cancelado hasta la fecha de pago", R.drawable.pagominimo));
        Glosariop.add(new Glosario("Plazo", "Tiempo entre la fecha de inicio y la fecha de finalización de un crédito", R.drawable.plazo));
        Glosariop.add(new Glosario("Políticas de crédito", "Conjunto de procedimientos que sigue una institución para otorgar préstamos a sus clientes", R.drawable.credito));
        Glosariop.add(new Glosario("Préstamo", "Suma de dinero entregada a un cliente por un plazo determinado", R.drawable.prestamo));
        //Glosario R
        Glosarior.add(new Glosario("Rendimiento", "Es la ganancia que se recibe por mantener un saldo mayor a cero en la cuenta de ahorros", R.drawable.rendimiento));
        //Glosario S
        Glosarios.add(new Glosario("Sujeto de crédito", "Empresa o persona que luego de un análisis por parte de la institución financiera es calificado para otorgarle un crédito ", R.drawable.sujetorendimiento));


        //Glosario T
        Glosariot.add(new Glosario("Tarjeta de crédito", "Es el medio de pago aplazado, que permiten a su titular efectuar gastos y retirar dinero de los cajeros automático", R.drawable.tarjetacredito));
        Glosariot.add(new Glosario("Tarjeta de débito", "Es el medio de pago al contado, que permite a su titular disponer de los fondos depositados en las cuentas bancarias a las que están asociadas", R.drawable.tarjeta));


    }

    private String descripcion;
    private String nombre;
    private int idDrawable;


    public Glosario(String des, String nombre, int idDrawable) {
        this.descripcion = des;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getPrecio() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
}
