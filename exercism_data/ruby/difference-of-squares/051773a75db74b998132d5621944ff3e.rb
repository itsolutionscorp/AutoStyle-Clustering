class Squares
	def initialize(number) 
	  @number = number
	end

	def square_of_sums 
	  sum_of_ranges = (1..@number).inject{|sum,x| sum + x }
	  sum_of_ranges**2
	end

	def sum_of_squares 
	  (1..@number).to_a.map!{|x| x**2 }.inject{|sum,x| sum + x }
	end

	def difference 
	  square_of_sums - sum_of_squares
	end
end
