class Squares

	def initialize(n)
		@num = n
	end

	def sum_of_squares()
		sum = 0
		(1..@num).each do |i| 
			sum += i**2
		end #each
		return sum
	end #sum_of_squares

	def square_of_sums()
		sum = 0
		(1..@num).each do |i|
			sum += i
		end #each
		return sum**2
	end #square_of_sums

	def difference()
		square_of_sums() - sum_of_squares()
	end #difference

end #class
