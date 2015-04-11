class Hamming
    @@distancia=0
  
    def Hamming.compute(primerCodigo,segundoCodigo)
        
        if primerCodigo.length==segundoCodigo.length
            #Recorres ambas cadenas y comparar caracter por caracter, y si hay diferencia sumarla al atributo distancia.
            for i in 0..primerCodigo.length-1
                if primerCodigo[i]!=segundoCodigo[i]
                    @@distancia+= 1
                end
            end
        end
        return @@distancia
    end
end
