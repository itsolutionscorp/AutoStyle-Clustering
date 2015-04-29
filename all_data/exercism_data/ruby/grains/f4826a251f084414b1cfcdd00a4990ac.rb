class Grains
	   
    
	def square(cuadrado)
		return 2**(cuadrado-1)
    end

    def total
    	
    	total=0
    	
        (1..64).each{ |num| total+=square(num)}
    	
    	return total

    end

end
