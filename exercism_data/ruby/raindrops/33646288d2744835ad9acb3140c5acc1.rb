require 'mathn'
# Clase Numero a string
class Raindrops
  def convert(numero)
    # prime_division Realizar la factorizacion prima del valor
    factores = numero.prime_division
    (0..factores.length - 1).each do |i|
      # Si responder es una cadena vacia, significa que es la primer vez y
      # si e cumple la primer condicion se debe asignar el nuevo valor o bien
      # si responder no coincide el string, significa que es la primer vez que
      # se va asignar, en caso de que coincida ya no debe concatenar la cadena
      # con la misma palabra
      @responder = case factores[i]
                   when 3 then transformar_pling(@responder)
                   when 5 then transformar_plang(@responder)
                   when 7 then transformar_plong(@responder)
                   end
    end
    # No se cumplieron las condiciones anteriores, entonces la variable toma el
    # valor del parametro de entrada y en caso de no tener ningun elemento en
    # la factorizacion, significa que es el numero 1
    @responder = numero.to_s if @responder.nil? || factores.nil?
    @responder
  end
  # Transformar a cadena Pling
  def transformar_pling(cadena)
    @cadena = cadena + 'Pling' if cadena.nil? || cadena.match('Pling').nil?
  end
  # Transformar a cadena Plang
  def transformar_plang(cadena)
    @cadena = cadena + 'Plang' if cadena.nil? || cadena.match('Plang').nil?
  end
  # Transformar a cadena Plong
  def transformar_plong(cadena)
    @cadena = cadena + 'Plong' if cadena.nil? || cadena.match('Plong').nil?
  end
end
