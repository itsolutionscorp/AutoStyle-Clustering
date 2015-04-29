class Fixnum
	def to_roman
		roman_string = ''
		number_array = number_to_array(self)
		positional_notation_count = number_array.count
		places_hash = {1 => 1, 2 => 10, 3 => 100, 4 => 1000}
		number_array.each do |digit|
			unless digit == '0'
				digit = digit.to_i
				numeral_of_digit = digit * places_hash[positional_notation_count]
				roman_string += translation_loop(numeral_of_digit, digit)
			end
			positional_notation_count -= 1
		end
		roman_string
	end

	def number_to_array(number)
		number.to_s.split('')
	end

	def translation_loop(number, digit)
		if number / 1000 >= 1
			translation_indicator = 7
		elsif number / 100 >= 1
			translation_indicator = 5
		elsif number / 10 >= 1
			translation_indicator = 3
		elsif number / 1 >= 1
			translation_indicator = 1
		end
		translate_digits(translation_indicator, number, digit)
	end

	def translate_digits(indicator, number, digit) 
		roman_numeral_options_string = 'IVXLCDM'
		lower_power = roman_numeral_options_string[indicator - 1]
		if indicator == 7
			power = ''
			higher_power = ''
		else
			power = roman_numeral_options_string[indicator]
			higher_power = roman_numeral_options_string[indicator + 1]
		end
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
