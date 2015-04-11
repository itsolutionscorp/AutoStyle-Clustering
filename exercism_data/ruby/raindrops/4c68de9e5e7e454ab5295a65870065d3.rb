require 'prime'
module Raindrops
	def self.convert(number)
		primes = Prime.prime_division(number).map{|pair| pair.first}
		return_string = primes.inject("") { |memo, prime|
			memo + case prime
			when 3 then "Pling"
			when 5 then "Plang"
			when 7 then "Plong"
			else ""
			end
		}
		if 0 == return_string.size
			return_string = number.to_s
		end
		return_string
	end
end
