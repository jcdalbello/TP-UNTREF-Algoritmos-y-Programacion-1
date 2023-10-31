public enum Estado {
    SALUDABLE,  // No causa nada
    QUEMADO,    // Reduce el ataque a la mitad
    PARALIZADO, // Reduce la defensa a la mitad
    ENVENENADO  // Baja un 1/8 de vida de la criatura por turno
}

// Se modifico el metodo de recibirDaño() en la clase Criatura para que se reduzca el valor de ataque o la defensa, segun corresponda
// Se modifico el metodo turno() en la clase PantallaDuelo para que, al terminar, reduzca la vida de las criaturas envenenadas
