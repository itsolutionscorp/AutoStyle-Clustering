require 'prime'

class Raindrops
	def self.convert num

		primes = num.prime_division.to_s

		output = ""

		if !/\D3\D/.match(primes).nil?
			output << "Pling"
		end

		if !/\D5\D/.match(primes).nil?
			output << "Plang"
		end

		if !/\D7\D/.match(primes).nil?
			output << "Plong"
		end

		if output.size == 0
			output << num.to_s
		end

		output

	end
end
