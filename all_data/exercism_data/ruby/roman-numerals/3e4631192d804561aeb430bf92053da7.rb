class Fixnum
	def to_roman 
		number = self
		numeral_hash = { 1000 => "M", 900 => "CM", 400 => "CD", 500 => "D", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}

		# generate array of numerals in decimal and order it
		numeral_array = numeral_hash.map { |index, item| index }.sort.reverse

		# get array of decimal numbers 1000, 900, etc.
		number_array = number_to_numeral(number, numeral_array, [])

		# turn decimal array into roman numeral string
		string_array = number_array.map { |element| numeral_hash[element] }.join
	end

	private

	def number_to_numeral(number, numerals, return_array)
		numerals_first = numerals[0]
		numerals_rest = numerals[1..-1]
		number_less_numeral = number - numerals_first

		if number_less_numeral < 0

			number_to_numeral(number, numerals_rest, return_array)

		elsif number_less_numeral > 0
			
			return_array.push(numerals_first)
			number_to_numeral(number_less_numeral, numerals, return_array)

		else number_less_numeral == 0
			
			return_array.push(numerals_first)
			return_array

		end
	end
end
