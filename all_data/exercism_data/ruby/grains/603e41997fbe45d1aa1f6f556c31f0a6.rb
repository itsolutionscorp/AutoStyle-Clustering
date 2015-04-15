class Grains
    def square(g)
        2**(g-1)
    end
    def total
    	(1..64).map {|g| square(g) }.reduce(:+)
    end
end
