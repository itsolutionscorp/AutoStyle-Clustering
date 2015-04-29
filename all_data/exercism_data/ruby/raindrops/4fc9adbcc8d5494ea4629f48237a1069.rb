class Raindrops 
	def self.convert(number)
		output_string = ""
		if (number%3 == 0) then output_string << "Pling" end
		if (number%5 == 0) then output_string << "Plang" end
		if (number%7 == 0) then output_string << "Plong" end
		if output_string.empty? then output_string << number.to_s end
		output_string
	end
end
