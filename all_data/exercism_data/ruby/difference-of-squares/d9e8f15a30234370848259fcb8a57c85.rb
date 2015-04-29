class Squares
	def initialize(n)
		@n = n
	end
    def square_of_sums
    	y = 0
        (1..@n).each{ |x|  y += x}
        y**2
    end
    def sum_of_squares
        y = 0
        (1..@n).each{ |x|  y += x**2}
        y
    end    
    def difference
    	square_of_sums - sum_of_squares
    end
end
