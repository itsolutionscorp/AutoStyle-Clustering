class Raindrops

	def self.convert(num)
		factor_array = num.factors
		if factor_array.include?(5) && factor_array.include?(7) && factor_array.include?(3)
			return 'PlingPlangPlong'	
		elsif factor_array.include?(3) && factor_array.include?(7)
			return 'PlingPlong'
		elsif factor_array.include?(5) && factor_array.include?(7)
			return 'PlangPlong'
		elsif factor_array.include?(3) && factor_array.include?(5)
			return 'PlingPlang'
		elsif factor_array.include?(5)
			return 'Plang'
		elsif factor_array.include?(7)
			return 'Plong'
		elsif factor_array.include?(3)
			return 'Pling'
		else 
			return num.to_s
		end
	end
end


class Integer

	def factors
		(1..self).select { |n| (self % n).zero? }
	end
end
