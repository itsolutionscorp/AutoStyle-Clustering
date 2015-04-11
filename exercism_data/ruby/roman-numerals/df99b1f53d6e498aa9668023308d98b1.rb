class Fixnum

	def to_roman
		digits = self.to_s.split('').map(&:to_i)
		result = []

		case digits.length
		when 1
			first_digit_value(result, digits[0])
		when 2
			second_digit_value(result, digits[0])
			first_digit_value(result, digits[1])
		when 3
			third_digit_value(result, digits[0])
			second_digit_value(result, digits[1])
			first_digit_value(result, digits[2])			
		when 4
			fourth_digit_value(result, digits[0])
			third_digit_value(result, digits[1])
			second_digit_value(result, digits[2])
			first_digit_value(result, digits[3])				
		end
		result.join
	end

	private

	def first_digit_value(result, digit)
		case digit
		when 1
			result << 'I'
		when 2
			result << 'II'
		when 3
			result << 'III'
		when 4
			result << 'IV'
		when 5
			result << 'V'
		when 6
			result << 'VI'
		when 7
			result << 'VII'
		when 8
			result << 'VIII'
		when 9
			result << 'IX'
		end
		result
	end

	def second_digit_value(result, digit)
		case digit
		when 1
			result << 'X'
		when 2
			result << 'XX'
		when 3
			result << 'XXX'
		when 4
			result << 'XL'
		when 5
			result << 'L'
		when 6
			result << 'LX'
		when 7
			result << 'LXX'
		when 8
			result << 'LXXX'
		when 9
			result << 'XC'
		end
		result
	end

	def third_digit_value(result, digit)
		case digit
		when 1
			result << 'C'
		when 2
			result << 'CC'
		when 3
			result << 'CCC'
		when 4
			result << 'CD'
		when 5
			result << 'D'
		when 6
			result << 'DC'
		when 7
			result << 'DCC'
		when 8
			result << 'DCCC'
		when 9 
			result << 'CM'
		end
		result
	end

	def fourth_digit_value(result, digit)
		case digit
		when 1
			result << 'M'
		when 2 
			result << 'MM'
		when 3
			result << 'MMM'
		end
		result
	end

end
