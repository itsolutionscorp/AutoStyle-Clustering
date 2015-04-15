class Squares
	attr_accessor :up_lim

	def initialize(up_lim)
		@up_lim = up_lim
	end
	
	def square_of_sums
		sum = 0
		(1..@up_lim).each do |n|
			sum += n
		end
		sum**2
	end

	def sum_of_squares
		sum = 0
		(1..@up_lim).each do |n|
			sum += n**2
		end
		sum
	end

	def difference
		square_of_sums - sum_of_squares
	end
	 
end
