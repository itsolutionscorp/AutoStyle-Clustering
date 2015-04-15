class Fixnum
	ROMAN = {
		'M' => 1000,
		'CM' => 900,
		'D' => 500,
		'CD' => 400,
		'C' => 100,
		'XC' => 90,
		'L' => 50,
		'XL' => 40,
		'X' => 10,
		'IX' => 9,
		'V' => 5,
		'IV' => 4,
		'III' => 3,
		'II' => 2, 
		'I' => 1
	}
	def to_roman
		num = self
		roman_value = ''
		until num == 0 do
			ROMAN.each{ |roman, value|
				if num % value != num then
					times, num = num.divmod(value)
					roman_value << roman * times
				end 
			}
		end
		roman_value
	end
end
