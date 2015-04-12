def compute(cadena1, cadena2)
    distancia = 0
    # Verificamos si longitud de cadena1 es mayor a la 2,
    # de ser asi, solo comparamos con la longitud de cadena2
    cadena1.length > cadena2.length ? longitud = cadena2.length :
    longitud = cadena1.length
    (0..longitud - 1).each do |i|
      cadena1[i] != cadena2[i] ? distancia += 1 : distancia
    end
    distancia
  end