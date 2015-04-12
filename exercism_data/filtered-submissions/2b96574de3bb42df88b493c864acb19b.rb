# Clase diferencia Hamming
class Hamming
  def compute(primerCodigo , segundoCodigo)
    if primerCodigo.length == segundoCodigo.length
    # Recorres ambas cadenas y comparar caracter por caracter, y si hay
    # diferencia sumarla al atributo distancia.
      (0..primerCodigo.length - 1).each do |i|
        @distancia += 1 unless primerCodigo[i] == segundoCodigo[i]
      end
    end
    @distancia = 0 if @distancia.nil?
  end
end
