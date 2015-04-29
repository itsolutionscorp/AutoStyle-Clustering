require 'prime'
class Raindrops
	
	def self.convert(number)
		prime_fact = number.prime_division.map(&:first)
		prime_arr = [3,5,7]
		target_arr = prime_fact & prime_arr
		case target_arr
			when [3]
				"Pling"
			when [5]
			 	"Plang"
			when [7]
				"Plong"
			when [3,5]
				"PlingPlang"
			when [3,7]
				"PlingPlong"
			when [5,7]
				"PlangPlong"
			when [3,5,7]
				"PlingPlangPlong"	
			else
				number.to_s
			end	
	end
end

Raindrops.convert(6)
