class PhoneNumber 

	def initialize number
		@number = "0000000000"
		@area_code = "000"
		@formated_number = "(000) 000-0000" 

		number_copy = number.gsub(/\D/, '')

		if number_copy.length == 10
			@number = number_copy
		elsif number_copy.length == 11 and number_copy[0] == "1"
			@number = number_copy[1..10]
		end

		if number_copy != "0000000000"
			@area_code = @number[0..2]
			@formatted_number = "(#{@area_code}) #{@number[3..5]}-#{@number[6..10]}"
		end
	end

	def to_s
		return @formatted_number
	end

	def number
		return @number
	end

	def area_code
		return @area_code
	end



end
