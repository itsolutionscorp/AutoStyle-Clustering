class Fixnum
	def to_roman
		num = self
		roman = ""

		roman += "M"*(num/1000)
		num = num % 1000

		roman += "CM" and num -= 900 if num >= 900

		roman += "D"*(num/500)
		num = num % 500

		roman += "CD" and num -= 400 if num >= 400

		roman += "C"*(num/100)
		num = num % 100

		roman += "XC" and num -= 90 if num >= 90 

		roman += "L"*(num/50)
		num = num % 50

		roman += "XL" and num -= 40 if num >= 40 

		roman += "IX" and return roman if num == 9

		roman += "X"*(num/10)
		num = num % 10

		roman += "IV" and return roman if num == 4

		roman += "V"*(num/5)
		num = num % 5

		roman += "I"*num

		roman
	end
end
