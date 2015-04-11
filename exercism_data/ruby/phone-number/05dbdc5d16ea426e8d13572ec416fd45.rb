class PhoneNumber
	attr_accessor :number, :area_code
	INVALID = "0000000000"

	def initialize (number)
		@number = strip_number(number)
		@area_code = @number[0..2].to_s
	end

	def strip_number (input)
		input = invalid(input)
		input.gsub!(/[^0-9]/, "")
		return INVALID if input.length > 11
		input[0] = '' if input.length == 11 else input
	end

	def to_s
		"(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
	end

		private
			def invalid (input) 
				return INVALID if (input =~ /[a-zA-Z]/ || input.length < 10 || (input.length == 11 && input[0] != '1')) else input
			end
			
end
