class PhoneNumber 
	def initialize(string)
	       @string = string
	       @number = number
	end
	
	def number
		invalid = "0000000000"
		result = @string.gsub(/[\.\(\)\-\s+]/, '')

		case
		when result.match(/[a-zA-Z]/)
			invalid
		when result.length < 10 || result.length > 11
			invalid
		when result.length == 11 && result.match(/^1/)
			@number = result[1..-1]
		when result.length == 11
			invalid
		else
			@number = result
		end	
	end
	
	def area_code
		@number[0..2]	
	end
	
	def to_s
		"("+ @number[0..2] + ") " + @number[3..5] +"-"+ @number[6..9]
	end
end
