require 'prime'

class Raindrops
	class << self
		def convert(num)

			factor_array = num.prime_division
			output = ""

			if factor_array.length
				for pair in factor_array
					case pair[0]
						when 3
							output += "Pling"
						when 5
							output += "Plang"
						when 7
							output += "Plong"
					end
				end
			end

			if output == ""
				output = num.to_s
			end

			output

		end
	end
end
