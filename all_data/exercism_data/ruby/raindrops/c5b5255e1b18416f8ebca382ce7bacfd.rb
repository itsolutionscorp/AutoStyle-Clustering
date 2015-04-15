require 'prime'

class Raindrops
	class << self

		def convert(num)
			if prime?(num)
				rain(num)
			else
				if num == 1
					num.to_s
				else
					factors = prime_factors(num)
					build_sounds(factors, num)
				end
			end
		end

		private

		def prime?(num)
			Prime.prime?(num)
		end

		def prime_factors(num)
			Prime.prime_division(num)
		end

		def rain(num)
			sound = ""
			case num
			when 3
				sound = "Pling"
			when 5
				sound = "Plang"
			when 7
				sound = "Plong"
			end
			sound
		end

		def build_sounds(factors, num)
			sound = ""
			factors.each do |f|
				if prime?(f[0])
					sound << rain(f[0])
				end
			end
			if sound != ""
				sound
			else
				num.to_s
			end
		end
	end
end
