class Squares
	def initialize num
		@value = num
	end

	def square_of_sums 
		 (1..@value).inject(&:+) ** 2
	end

	def sum_of_squares
		 (1..@value).map{|x| x**2}.inject(&:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end
end
