import java.util.*;

public class Parqueaderopublico {
    static Scanner sc = new Scanner(System.in);
    static List<Vehiculo> vehiculos = new ArrayList<>();
    static Stack<Vehiculo> vehiculos2Ruedas = new Stack<>();
    static Stack<Vehiculo> vehiculos4Ruedas = new Stack<>();
    static int contadorVehiculos = 0;
    static int valorTotal = 0;

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("------ PARQUEADERO ------");
            System.out.println("1. Ingreso de vehículo");
            System.out.println("2. Visualizar tabla actualizada");
            System.out.println("3. Visualizar vehículos de 2 ruedas");
            System.out.println("4. Visualizar vehículos de 4 ruedas");
            System.out.println("5. Cantidad de vehículos y valor total");
            System.out.println("6. Eliminar vehículo");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> ingresarVehiculo();
                case 2 -> mostrarTabla();
                case 3 -> mostrarVehiculos2Ruedas();
                case 4 -> mostrarVehiculos4Ruedas();
                case 5 -> mostrarCantidadYValor();
                case 6 -> eliminarVehiculo();
                case 7 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 7);
    }

    public static void ingresarVehiculo() {
        contadorVehiculos++;
        System.out.print("Ingrese la placa del vehículo: ");
        String placa = sc.next();
        System.out.print("Ingrese el tipo de vehículo (1 - bicicleta / 2 - motocicleta / 3 - carro): ");
        int tipo = sc.nextInt();
        System.out.print("Ingrese la hora de ingreso del vehículo (en formato 24 horas): ");
        int horaIngreso = sc.nextInt();
        int valor = 0;
        switch (tipo) {
            case 1 -> valor = 20;
            case 2 -> valor = 30;
            case 3 -> valor = 60;
            default -> {
                System.out.println("Tipo de vehículo inválido.");
                return;
            }
        }
        Vehiculo vehiculo = new Vehiculo(contadorVehiculos, placa, tipo, horaIngreso, valor);
        vehiculos.add(vehiculo);
        if (tipo == 1 || tipo == 2) {
            vehiculos2Ruedas.push(vehiculo);
        } else {
            vehiculos4Ruedas.push(vehiculo);
        }
        System.out.println("Vehículo ingresado correctamente.");
    }

    public static void mostrarTabla() {
        System.out.println("------ TABLA DE VEHÍCULOS ------");
        System.out.println("N°\tPLACA\tTIPO\tHORA DE INGRESO\tVALOR A PAGAR");
        for (Vehiculo vehiculo : vehiculos) {
           
            System.out.println(vehiculo.getNumero() + "\t" + vehiculo.getPlaca() + "\t" + vehiculo.getTipo() + "\t" + vehiculo.getHoraIngreso() + "\t\t\t" + vehiculo.getValorAPagar());
        }
    }

    public static void mostrarVehiculos2Ruedas() {
        System.out.println("------ VEHÍCULOS DE 2 RUEDAS ------");
        System.out.println("N°\tPLACA\tTIPO\tHORA DE INGRESO\tVALOR A PAGAR");
        while (!vehiculos2Ruedas.empty()) {
            Vehiculo vehiculo = vehiculos2Ruedas.pop();
            System.out.println(vehiculo.getNumero() + "\t" + vehiculo.getPlaca() + "\t" + vehiculo.getTipo() + "\t" + vehiculo.getHoraIngreso() + "\t\t\t" + vehiculo.getValorAPagar());
            valorTotal += vehiculo.getValorAPagar();
        }
    }

    public static void mostrarVehiculos4Ruedas() {
        System.out.println("------ VEHÍCULOS DE 4 RUEDAS ------");
        System.out.println("N°\tPLACA\tTIPO\tHORA DE INGRESO\tVALOR A PAGAR");
        while (!vehiculos4Ruedas.empty()) {
            Vehiculo vehiculo = vehiculos4Ruedas.pop();
            System.out.println(vehiculo.getNumero() + "\t" + vehiculo.getPlaca() + "\t" + vehiculo.getTipo() + "\t" + vehiculo.getHoraIngreso() + "\t\t\t" + vehiculo.getValorAPagar());
            valorTotal += vehiculo.getValorAPagar();
        }
    }

    public static void mostrarCantidadYValor() {
        System.out.println("------ CANTIDAD DE VEHÍCULOS Y VALOR TOTAL ------");
        System.out.println("Cantidad de vehículos en parqueadero: " + vehiculos.size());
        System.out.println("Valor total a pagar: " + valorTotal);
    }

    public static void eliminarVehiculo() {
        System.out.print("Ingrese el número del vehículo que desea eliminar: ");
        int numero = sc.nextInt();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumero() == numero) {
                vehiculos.remove(vehiculo);
                System.out.println("Vehículo eliminado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún vehículo con ese número.");
    }
}

class Vehiculo {
    private final int numero;
    private final String placa;
    private final int tipo;
    private final int horaIngreso;

    public Vehiculo(int numero, String placa, int tipo, int horaIngreso, int valorAPagar) {
        this.numero = numero;
        this.placa = placa;
        this.tipo = tipo;
        this.horaIngreso = horaIngreso;
    }

    public int getNumero() {
        return numero;
    }

    public String getPlaca() {
        return placa;
    }

    public int getTipo() {
        return tipo;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public int getValorAPagar() {
        int horaActual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutosTranscurridos = (horaActual - horaIngreso);
        int valorAPagar = 0;
        switch (tipo) {
            case 1 -> // Bicicletas y ciclomotores
                valorAPagar = minutosTranscurridos * 20;
            case 2 -> // Motocicletas
                valorAPagar = minutosTranscurridos * 30;
            case 3 -> // Carros
                valorAPagar = minutosTranscurridos * 60;
        }
        return valorAPagar;
    }
}
