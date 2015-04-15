require 'prime'

class Raindrops
	def self.convert num
		string = ""

		num.prime_division.each do |pair|
			if pair[0] == 3 then
				string += "Pling"
			elsif pair[0] == 5 then
				string += "Plang"
			elsif pair[0] == 7 then
				string += "Plong"
			end
		end

		string.length > 1 ? string : num.to_s
	end
end
