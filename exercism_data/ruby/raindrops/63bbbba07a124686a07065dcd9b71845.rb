require 'pry'

class Raindrops
	PRIMES = { 
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		factors = PRIMES.select{ |prime, value| (number % prime) == 0 }.values.join

		factors.empty? ? number.to_s : factors
	end

end
