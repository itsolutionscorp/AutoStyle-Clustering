class Fixnum
	def to_roman
		num = self
		roman_num = ""
		conversions = {
										1000 => "M",
									 	900  => "CM",
									 	500  => "D",
									 	400  => "CD",
									 	100  => "C",
									 	90   => "XC",
									 	50   => "L",
									 	40   => "XL",
									 	10   => "X",
									 	9    => "IX",
									 	5    => "V",
									 	4    => "IV",
									 	1    => "I"
									 }
		conversions.each do |k, v|
			if num >= k
				roman_num += v*(num/k)
				num -= (num/k)*k
			end
		end
		roman_num
	end
end
