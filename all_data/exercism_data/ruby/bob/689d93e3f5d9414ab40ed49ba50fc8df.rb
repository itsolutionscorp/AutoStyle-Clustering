class Bob
    #Método hey a heredar
    def hey (cadena)
        #Expresión busca caracteres que no sean espacios en blanco, y si no hay
        #manda nil, es decir la cadena esta formada por espacios en blanco
        if /\S/.match(cadena)!=nil
            if cadena.index('?') == cadena.length-1
                return 'Sure'
            end
            if cadena.upcase == cadena
                return 'Woah, chill out!'
            else
                    return 'Whatever.'
            end
        else
            return 'Fine. Be that way!'
        end
        
    end
end
