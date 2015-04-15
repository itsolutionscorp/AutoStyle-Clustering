class Raindrops
	def self.convert(number)
		output_string = '' 

		if number % 3 == 0
			 output_string << 'Pling'
		end

		if number % 5 == 0
			output_string << 'Plang'
		end
		
		if number % 7 == 0
			output_string << 'Plong'
		end

		if output_string == ''
			output_string << number.to_s
		end	

		return output_string
	end
end
