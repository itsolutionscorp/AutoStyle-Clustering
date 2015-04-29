class Fixnum
	def to_roman
		roman_numeral = ""
		given_number = self

    roman_cipher.keys.each do |divisor|
			quotient, modulus = given_number.divmod(divisor)
			roman_numeral << roman_cipher[divisor] * quotient
			given_number = modulus
		end

		roman_numeral
	end

	private

	def roman_cipher
		{ 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD",
		100  => "C", 90  => "XC", 50  => "L", 40  => "XL",
		10   => "X", 9   => "IX", 5   => "V", 4   => "IV",
		1    => "I" }
  end
end
