class PhoneNumber
	def initialize( phone_number )
		@phone_number = phone_number.gsub( /\D/, '' )
		verify_format if @phone_number.length != 10
	end

	def verify_format
		if @phone_number.start_with?( '1' ) && @phone_number.length == 11
			@phone_number[0] = ''
		else
			@phone_number = '0000000000'
		end
	end

	def number
		@phone_number
	end

	def area_code
		@phone_number[0..2]
	end

	def to_s
		"(#{@phone_number[0..2]}) #{@phone_number[3..5]}-#{@phone_number[6..9]}"
	end
end
