class Grains
        def initialize
            @totalGranos=0
            @totalCuadros=64
        end
        #Potencia de 2 a la n-1
        def square(numCuadro)
                return 2 ** (numCuadro-1)
        end
        def totalGranos
            for i in 1..@totalCuadros
                @totalGranos+=square(i)
            end
        end
        def total
            totalGranos
            return @totalGranos
        end
end
