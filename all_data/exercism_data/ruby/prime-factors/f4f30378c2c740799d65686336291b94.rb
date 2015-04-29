class PrimeFactors
	def self.for(number)
		last_factor = 2
		factors = []
		
		while number > 1 do
			while (number.modulo(last_factor) == 0) do
				factors << last_factor
				number/= last_factor
			end
			last_factor+= 1
		end
		factors
	end
end
