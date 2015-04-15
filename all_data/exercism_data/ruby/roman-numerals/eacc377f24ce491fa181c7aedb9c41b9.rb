class Fixnum
	def to_roman
		roman_letters = { 1 => "I",
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
		number = self
		s = roman_letters.sort do |a, b| b <=> a end
		roman_letters.sort do |a, b| b <=> a end.reduce("") do |acc, (key, value)|
			count = number / key
			number %= key
			acc << value * count
		end
	end
end
