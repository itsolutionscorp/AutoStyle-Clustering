class Fixnum
	def sqr
		self**2
	end
end
class Squares
    def initialize(n)
        @n = n
    end
    def square_of_sums
        (1..@n).reduce(:+)**2
    end
    def sum_of_squares
        (1..@n).map(&:sqr).reduce(:+)
    end
    def difference
        square_of_sums - sum_of_squares
    end
end
