class Fixnum

	def to_roman
		"#{thousands(self)}#{fivehundred(self)}#{hundreds(self)}#{fifty(self)}#{tens(self)}#{five(self)}#{ones(self)}"
	end

	private

	def thousands(num)
		roman = ""
		(num / 1000).times {|x|	roman += "M"}
		roman		
	end

	def fivehundred(num)
		num %= 1000
		return "CM" if num >= 900
		return "D" if num >= 500
	end

	def hundreds(num)
		roman = ""
		num %= 1000
		return "" if num >= 900
		num %= 500
		return "CD" if num >= 400
		(num / 100).times {|x| roman += "C"}
		roman
	end

	def fifty(num)
		num %= 100
		return "XC" if num >= 90
		return "L" if num >= 50
	end		

	def tens(num)
		roman = ""
		num %= 100
		return "" if num >= 90
		num %= 50
		return "XL" if num >= 40
		(num / 10).times {|x| roman += "X"}
		roman
	end

	def five(num)
		num %= 10
		return "IX" if num == 9
		return "V" if num >= 5
	end	

	def ones(num)
		roman = ""
		num %= 10
		return "" if num == 9
		num %= 5
		return "IV" if num == 4
		(num).times {|x| roman += "I"}
		roman
	end	

end
