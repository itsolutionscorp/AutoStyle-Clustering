# Clase Transform
class ETL
  def self.transform(oldSys)
  # Hash.new
    h1 = {}
    oldSys.each_pair do |clave , valor|
    # Recorremos el arreglo de valor y lo agregamos al diccionario,
    # conservando la posicion o clave como valor
      (0..valor.length - 1).each do |i|
        h1[valor[i].downcase] = clave
      end
    end
    h1
  end
end
