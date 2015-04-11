require 'mathn'
class Raindrops
    def convert(numero)
        #prime_division Realizar la factorización prima del valor
        factores=numero.prime_division
        responder=""

        for i in 0..factores.length - 1
            #Si responder es una cadena vacia, significa que es la primer vez y si e cumple la primer condicion
            #se debe asignar el nuevo valor o bien si responder no coincide el string, significa que
            #es la primer vez que se va asignar, en caso de que coincida ya no debe concatenar la cadena con la
            #misma palabra
            if factores[i][0]== 3 and (responder.length==0 or responder.match('Pling')==nil)
                responder=responder + 'Pling'
            else if factores[i][0]== 5 and (responder.length==0 or responder.match('Plang')==nil)
                    responder=responder + 'Plang'
                    
                    else if factores[i][0]== 7 and (responder.length==0 or responder.match('Plong')==nil)
                            responder=responder + 'Plong'
                    end
                end
            end
        end
        #No se cumplieron las condiciones anteriores, entonces la variable toma el valor del parámetro de entrada
        if responder.length==0
            responder= numero.to_s
        end
        #El uno es uno primo
        if factores.length==0
            responder='1'
        end
        return responder
    end
end
