class ETL
    def ETL.transform(oldSys)
        h1=Hash.new
        oldSys.each_pair do |clave,valor|
            #Recorremos el arreglo de valor y lo agregamos al diccionario, conservando la posición o clave como valor
            for i in 0..valor.length-1
                h1[valor[i].downcase]=clave
            end
        end
        return h1
    end
end
