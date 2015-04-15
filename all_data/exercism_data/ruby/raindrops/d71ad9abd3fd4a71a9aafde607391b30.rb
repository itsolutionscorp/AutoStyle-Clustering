require 'prime'
require 'pry'

class Raindrops

	def self.convert(number)
		# prime_array = [3,5,7]
		# factor_array = number.prime_division.transpose.shift
		
		# if factor_array != nil
		# 	prime_factor_array = factor_array & prime_array
		# 		if prime_factor_array.eql?([3,5])
		# 			"PlingPlang"
		# 		elsif prime_factor_array.eql?([3,7])
		# 			"PlingPlong"
		# 		elsif prime_factor_array.eql?([3])
		# 			"Pling"
		# 		elsif prime_factor_array.eql?([5])
		# 			"Plang"
		# 		else prime_factor_array.eql?([7])
		# 			"Plong"
		# 		end
		# else 
		# 	number.to_s

		# end
		
		if number % 3 == 0 && number % 5 == 0 && number % 7 != 0
			"PlingPlang"
		elsif number % 3 == 0 && number % 7 == 0 && number % 5 != 0
			"PlingPlong"	
		elsif number % 5 == 0 && number % 7 == 0 && number % 3 != 0
			"PlangPlong"
		elsif number % 3 == 0 && number % 5 == 0 && number % 7 == 0
			"PlingPlangPlong"
		
		elsif number % 3 == 0
			return "Pling"
		elsif number % 5 == 0
			return "Plang"
		elsif number % 7 == 0
			return "Plong"
		
		else number.to_s
		end
	end
end
