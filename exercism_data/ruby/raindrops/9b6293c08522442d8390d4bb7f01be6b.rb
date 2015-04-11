require 'prime'
class Raindrops
	def self.convert(number)		
		values = [3, 5, 7]
		primes = Prime.prime_division(number).flatten
		raindrop_speak = ''
		unless number == 1
			primes.each { |prime|
				if prime == 3
					raindrop_speak << "Pling"
				elsif prime == 5 
					raindrop_speak << "Plang"
				elsif prime == 7
					raindrop_speak << "Plong"					
				end
			} 
		else 
			raindrop_speak = '1'
		end
		raindrop_speak = "#{number}" if (primes & values).empty?
		raindrop_speak
	end
end
