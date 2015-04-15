class PhoneNumber

	attr_accessor :number

	def initialize(phone_number)
		@number = sanitize_number(phone_number)
	end

	def sanitize_number(phone_number)
		if !!phone_number.match(/[a-zA-Z]/)
			return "0000000000"
		else
			phone_number = phone_number.gsub(/\D/, '')
		end

		if phone_number.length < 10
			return "0000000000"
		end

		if phone_number.length == 11
			if phone_number[0] == "1"
				phone_number = phone_number[1..-1]
			else
				return "0000000000"
			end
		end

		if phone_number.length > 11
			return "0000000000"
		end

		return phone_number
	end

	def area_code
		number[0..2]
	end

	def to_s
		"(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
	end

end
