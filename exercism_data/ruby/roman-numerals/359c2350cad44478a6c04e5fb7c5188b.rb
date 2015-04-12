class Integer
	ROMAN_MAP = { 	1 => "I",
                  		4 => "IV",
                  		5 => "V",
                  		9 => "IX",
                  		10 => "X",
                  		40 => "XL",
                  		50 => "L",
                  		90 => "XC",
                  		100 => "C",
                  		400 => "CD",
                  		500 => "D",
                  		900 => "CM",
                  		1000 => "M" }
	def to_roman
		i = self
		ROMAN_MAP.sort.reverse.each_with_object("") do |(int, rom), num|
			while i >= int
				num << rom
				i -= int
			end
		end
	end
end