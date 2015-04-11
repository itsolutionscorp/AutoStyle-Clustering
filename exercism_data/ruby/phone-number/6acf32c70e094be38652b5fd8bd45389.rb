class PhoneNumber
	def initialize(s_phone_number)
		@s_phone_number = s_phone_number
	end

	def number
	  number = @s_phone_number.gsub(/[^\d]/,'')
	  if number.size == 10
	  	return number
	  elsif number.size == 11 and number[0] == '1'
	  	return number[1,10]
	  end
	else
		return '0000000000'
	end

	def to_s
		num = number
		return "(#{num[0,3]}) #{num[3,3]}-#{num[6,4]}"
	end

	def area_code
		num = number
		return num[0,3]
	end
end
