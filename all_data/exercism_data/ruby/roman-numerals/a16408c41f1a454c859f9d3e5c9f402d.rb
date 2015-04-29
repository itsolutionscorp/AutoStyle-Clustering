class Fixnum
	def to_roman
		case
		when self < 4
			return "I" * self
		when self == 4
			return "IV"
		when self>= 5 && self< 9
			return "V" + (self - 5).to_roman
		when self == 9 
			return "IX"
		when self >= 10 && self< 40
			return "X" + (self - 10).to_roman
		when self >= 40 && self< 50
			return "XL" + (self - 40).to_roman
		when self >= 50 && self< 90
			return "L" + (self - 50).to_roman
		when self >= 90 && self< 100
			return "XC" + (self - 90).to_roman
		when self >= 100 && self< 400
			return "C" + (self - 100).to_roman
		when self >= 400 && self < 500
			return "CD" + (self - 400).to_roman
		when self >= 500 && self< 900
			return "D" + (self - 500).to_roman
		when self >= 900 && self < 1000
			return "CM" + (self - 900).to_roman
		when self >= 1000
			return "M" + (self - 1000).to_roman
		end
	end
end
