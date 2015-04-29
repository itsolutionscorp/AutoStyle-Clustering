class Squares
	attr_accessor :value
	
	def initialize (new_value)
		@value = new_value
	end

	def square_of_sums
		sos = 0;
		1.upto(value) do | val |
			sos += val
		end
		sos**2
	end

	def sum_of_squares
		sos = 0;
		1.upto(value) do | val |
			sos += (val)**2
		end
		sos
	end

	def difference 
		square_of_sums - sum_of_squares
	end
end
