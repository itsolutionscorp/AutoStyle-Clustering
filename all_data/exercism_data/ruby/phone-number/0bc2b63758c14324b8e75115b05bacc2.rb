class PhoneNumber
	INVALID_NUMBER = "0000000000"	
	AREA_CODE = 0..2
	FIRST_HALF = 3..5
	SECOND_HALF = 6..9
	
	attr_reader :number
	
	def initialize(formatted_number)
		@number = clean_number(formatted_number)
	end
	
	def area_code
		extract(AREA_CODE)
	end
	
	def to_s
		"(#{area_code}) #{extract(FIRST_HALF)}-#{extract(SECOND_HALF)}"
	end

private
	def clean_number(formatted)
		return INVALID_NUMBER if has_letters?(formatted)
		number = formatted.scan(/[0-9]+/).join
		return number if regular?(number)
		return number.slice(1, 10) if has_country_code?(number)
		return INVALID_NUMBER
	end
	
	def has_letters?(formatted)
		!formatted.scan(/[a-zA-Z]+/).empty?
	end
	
	def regular?(number)
		number.length == 10
	end
	
	def has_country_code?(number)
		(number.length == 11) && number.start_with?("1")
	end
	
	def extract(portion)
		@number.slice(portion)
	end
end
