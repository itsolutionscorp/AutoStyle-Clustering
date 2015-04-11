class Grains
    
    SQUARES = 64
    
    def square( n )
        # Gets you number of values for n bits
        # Subtract one as counting starts at square 1 (not 0)
        2 ** ( n - 1 )
    end
    
    def total
        # Represent squares as bits then the total number of grains
        # is equal to the integer value of 64 bits all 1s. Or rather
        # the sum of bits. 
        square( SQUARES + 1 ) - 1
    end
    
end
