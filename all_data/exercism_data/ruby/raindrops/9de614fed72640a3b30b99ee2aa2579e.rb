class Raindrops
	class << self
		def convert(number)
			response_string = ""
			primes = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
			primes.each do |prime, response|
				if prime_factor?(prime, number)
					response_string += response
				end
			end
			response_string = number.to_s if response_string == ""
			response_string
		end

		def prime_factor?(prime_factor, number)
			if (number/prime_factor) == (number/prime_factor.to_f)
				true
			end
		end
	end
end
