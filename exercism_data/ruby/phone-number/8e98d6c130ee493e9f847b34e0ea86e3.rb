require 'pry'

class PhoneNumber

	def initialize(string)
		@number = string
	end

	def number
		return bad_number if letter_check(@number)
		num = tokenize(@number)
		check_eleven_digits_with_one(num)
		length_check(num)
	end

	def area_code
		number[0..2]
	end

	def to_s
		"(#{area_code}) #{middle_numbers}-#{end_numbers}"
	end

	private

	def length_check(number)
		if number.length < 10 or number.length > 10
			bad_number
		else
			number
		end
	end

	def check_eleven_digits_with_one(number)
		if number.length == 11 and number[0] == "1"
			number[0] = ''
			number
		else
			bad_number
		end
	end

	def letter_check(number)
		number.match(/[a-z]/)
	end

	def middle_numbers
		number[3..5]
	end

	def end_numbers
		number[6..9]
	end

	def tokenize(number)
		number.gsub(/\D/, "")
	end

	def bad_number
		"0000000000"
	end
end
