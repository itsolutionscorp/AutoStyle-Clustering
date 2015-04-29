class PhoneNumber

	def initialize(number)
		@phone_num = sanitize(number)
	end

	def number
		@phone_num
	end

	def area_code
		@phone_num[0..2]
	end

	def to_s
		"(#{area_code}) #{@phone_num[3..5]}-#{@phone_num[6..9]}"
	end

	private

	def sanitize(number)
		number = number.gsub(/[^a-zA-Z\d]/, '')
		return '0' * 10 if contains_letters?(number) or wrong_length?(number)

		if number.length == 11 && number.start_with?('1')
			return number[1..-1]
		else
			return number
		end
	end

	def contains_letters?(number)
		true if number.match(/[a-zA-Z]/)
	end

	def wrong_length?(number)
		return true if number.length < 10 || number.length > 11
		return true if number.length == 11 && !number.start_with?('1')
	end

end
