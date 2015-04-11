class Squares
	attr_accessor :my_num

	def initialize(my_num)
		@my_num = my_num
	end

	def sum_of_squares
		return (((1..my_num).to_a).inject{|sum, x| sum + (x**2)})
	end

	def square_of_sums
		return ((((1..my_num).to_a).inject{|sum, x| sum + x})**2)
	end

	def difference
		return (square_of_sums - sum_of_squares)
	end
end
