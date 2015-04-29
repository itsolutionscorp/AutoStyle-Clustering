require 'pry'

class Raindrops
	PRIMES = { 
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}

	def self.convert(number)
		factors = PRIMES.keys.select{ |prime| (number % prime) == 0 }

		factors << number if factors.empty?
			
		factors.collect { |factor| PRIMES.fetch(factor, number)}.join	
	end

end
