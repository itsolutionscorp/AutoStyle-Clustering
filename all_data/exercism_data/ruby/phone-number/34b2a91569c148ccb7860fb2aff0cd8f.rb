class PhoneNumber

	def initialize(phone_number_raw)
		@phone_number = phone_number_raw
	end

	def number
		@phone_number = '0000000000' if /[a-zA-Z]+/.match(@phone_number)
		@phone_number = @phone_number.gsub(/[\D]+/, '')	
		if @phone_number.length == 11 
			@phone_number[0] == '1' ? call = @phone_number[1, (@phone_number.length - 1)] : call = '0000000000'
		elsif @phone_number.length < 10 || @phone_number.length > 11 || @phone_number == nil
			call = '0000000000'
		else		
			call = @phone_number
		end	
		call
	end
	
	def area_code
		number[0..2]
	end
	
	def to_s
		'(' + number[0..2].to_s + ') ' + number[3..5].to_s + '-' + number[6..9].to_s
	end
end
