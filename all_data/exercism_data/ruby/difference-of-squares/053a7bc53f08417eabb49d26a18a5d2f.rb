class Squares
	def initialize(n)
		@number = n
	end

	def square_of_sums   # using reduce and abs2 methods saves a net of 2 lines of code
		#sum = 0
		#(1..@number).each {|i| sum += i}
		#sum = sum * sum
		(1..@number).reduce(:+).abs2
	end

	def sum_of_squares	 # using map and reduce methods saved a net of 2 lines of code	
		#sum = 0
		#(1..@number).each {|i| sum += i.abs2 }
		#sum 
		(1..@number).map(&:abs2).reduce(:+)
	end

	def difference
		square_of_sums - sum_of_squares
	end

end
