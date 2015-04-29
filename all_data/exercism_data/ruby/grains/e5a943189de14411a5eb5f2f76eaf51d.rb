class Grains
    def calculo ()
         @calculo = 0
         for i in 1..64
            @calculo += square(i)
        end
        return @calculo
    end
    
    def square (numero)
        return  2**(numero-1)
    end
end
