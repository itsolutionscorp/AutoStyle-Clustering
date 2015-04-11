require 'prime'

class Raindrops
	def self.convert(i)
		result = String.new

		Prime.prime_division(i).each do |prime|
			case prime.first
				when 3
					result += 'Pling'
				when 5
					result += 'Plang'
				when 7
					result += 'Plong'
					break
			end
		end

		return i.to_s if result == '' 

		result
	end
end
