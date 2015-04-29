class Fixnum
	def to_roman
		roman_string = ''
		number_array = number_to_array(self)
		digits_count = number_array.count
		notation_hash = {1 => 1, 2 => 10, 3 => 100, 4 => 1000}
		number_array.each do |digit|
			unless digit == '0'
				digit = digit.to_i
				numeral_of_digit = digit * notation_hash[digits_count]
				translation_string = find_roman_numerals(numeral_of_digit)
				roman_string += translate_roman_numerals(translation_string, digit)
			end
			digits_count -= 1
		end
		roman_string
	end

	def number_to_array(number)
		number.to_s.split('')
	end

	def find_roman_numerals(number)
		case 
		when number / 1000 >= 1
			translation_string = 'MQR'
		when number / 100 >= 1
			translation_string = 'CDM'
		when number / 10 >= 1
			translation_string = 'XLC'
		when number / 1 >= 1
			translation_string = 'IVX'
		end
		translation_string
	end

	def translate_roman_numerals(string, digit) 
		lower_power = string[0]
		power = string[1]
		higher_power = string[2]
		translation_array = [lower_power, 
						lower_power * 2, 
						lower_power * 3, 
						lower_power + power, 
						power, 
						power + lower_power, 
						power + (lower_power * 2), 
						power + (lower_power * 3),
						lower_power + higher_power, 
						higher_power]
		translation_array[digit - 1]
	end
end
