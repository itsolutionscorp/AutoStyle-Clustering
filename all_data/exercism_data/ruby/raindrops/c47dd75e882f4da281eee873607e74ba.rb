require 'prime'

class Raindrops
	def self.convert (number)
		result = ''
		if number == 1
  			result << 1.to_s
		else
			number.prime_division.each do |i|
				i.each do |j|
					if j > 9
						result = number.to_s
						break
					else
						result << 'Pling' if j == 3
						result << 'Plang' if j == 5
						result << 'Plong' if j == 7
					end
				end
	 		end
	 	end
 		return result
	end
end
