class Raindrops

	def self.convert(number)
		output = String.new
		output += 'Pling' if number % 3 == 0
		output += 'Plang' if number % 5 == 0
		output += 'Plong' if number % 7 == 0
		if output == ''
			return number.to_s
		else
			return output
		end
	end

end
