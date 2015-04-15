class Fixnum

	ARABIC_TO_ROMAN = {
		1000 => 'M',
		900 => 'CM',
		500 => 'D',
		400 => 'CD',
		100 => 'C',
		90 => 'XC',
		50 => 'L',
		40 => 'XL',
		10 => 'X',
		9 => 'IX',
		5 => 'V',
		4 => 'IV',
		1 => 'I'
	}

	def to_roman
		num = self
		return '' if num < 1 || !(num.is_a? Integer)

		roman_num = ''

		ARABIC_TO_ROMAN.each do |arabic, roman|
			roman_num << roman * (num/arabic)
			num = num % arabic
		end

		roman_num
	end
end
