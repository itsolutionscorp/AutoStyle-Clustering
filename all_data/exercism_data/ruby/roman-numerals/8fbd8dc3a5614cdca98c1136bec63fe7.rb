module RomanNumerals
	def to_roman
		result = ""
		currNum = self

		while currNum > 0 do
			if (currNum >= 1000)
				result += "M"
				currNum -= 1000
			elsif (currNum >= 900)
				result += "CM"
				currNum -= 900
			elsif (currNum >= 500)
				result += "D"
				currNum -=500
			elsif (currNum >= 400)
				result += "CD"
				currNum -= 400
			elsif (currNum >= 100)
				result += "C"
				currNum -=100
			elsif (currNum >= 90)
				result += "XC"
				currNum -= 90
			elsif (currNum >= 50)
				result += "L"
				currNum -=50
			elsif (currNum >= 40)
				result += "XL"
				currNum -= 40
			elsif (currNum >= 10)
				result += "X"
				currNum -=10	
			elsif (currNum >= 9)
				result += "IX"
				currNum -= 9	
			elsif (currNum >= 5)
				result += "V"
				currNum -=5
			elsif (currNum >= 4)	
				result += "IV"
				currNum -= 4
			else
				result += "I"
				currNum -= 1
			end
		end
		return result
	end
end

class Fixnum; include RomanNumerals; end
