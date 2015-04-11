class ETL

  def self.transform(fuente)
    salida = {}
    fuente.each do |key, value|
        value.each do |letra|
            salida[letra.downcase] = key
        end
    end
    return salida
  end
  
end

