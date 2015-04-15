require 'Prime'

class Raindrops
	def self.convert(num)
		factorization = Prime.prime_division(num).flat_map { |factor, power| [factor] * power }
		return_str = ""

		if !factorization.include? 3 and !factorization.include? 5 and !factorization.include? 7 then
			return_str << num.to_s
		else
			if factorization.include? 3
				return_str << "Pling"
			end
			if factorization.include? 5
				return_str << "Plang"
			end
			if factorization.include? 7
				return_str << "Plong"
			end			
		end

		return return_str
	end
end
