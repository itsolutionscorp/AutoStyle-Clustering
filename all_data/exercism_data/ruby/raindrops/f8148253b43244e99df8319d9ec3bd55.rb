require 'prime'
require 'debugger'


class Raindrops

	def self.convert(num)
		string = ''
		factors = num.prime_division.collect{ |m| m[0]}
		unless (factors & [3, 5, 7]).empty?


				factors.each do |element|
					if element == 3
							string << 'Pling'
					end
					if element == 5
							string << 'Plang'
					end
					if element == 7
							string << 'Plong'
					end
				end	
			 string
		else
			 num.to_s
		end
	end



end

Raindrops.convert(105)
