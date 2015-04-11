=begin

first I need to learn more about roman numerals

I	1
V	5
X	10
L	50
C	100
D	500
M	1,000

We are going to stop at 3000

Example cases

48

XLVIII

10 50 5 1 1 1 =

I need some way to check that if it can be written as the penultimate of some numeral then we do so.

=end

class Fixnum
	def to_roman
		num_integer = self
		roman_numeral_string = ""

		while num_integer >= 1000
			roman_numeral_string += "M"
			num_integer -= 1000
		end

		if num_integer - 900 <= 100 && num_integer - 900 >= 0
			roman_numeral_string += "CM"
			num_integer -= 900
		end

		while num_integer >= 500
			roman_numeral_string += "D"
			num_integer -= 500
		end

#i have to check against 100 or 10?
		if num_integer - 400 <= 100 && num_integer - 400 >= 0
			roman_numeral_string += "CD"
			num_integer -= 400
		end

		while num_integer >= 100
			roman_numeral_string += "C"
			num_integer -= 100
		end

		if num_integer - 90 <= 10 && num_integer - 90 >= 0
			roman_numeral_string += "XC"
			num_integer -= 90
		end


		while num_integer >= 50
			roman_numeral_string += "L"
			num_integer -= 50
		end

#--------------------------------CASE: XL
		if num_integer - 40 <= 10 && num_integer - 40 >= 0 
			roman_numeral_string += "XL"
			num_integer -= 40
		end
#---------------------------------------------------

		while (num_integer >= 10)
			roman_numeral_string += "X"
			num_integer -= 10
		end

		if num_integer == 9
			roman_numeral_string += "IX"
			num_integer -= 9
		end

		while (num_integer >= 5)
			roman_numeral_string += "V"
			num_integer -= 5
		end


		if num_integer == 4
			roman_numeral_string += "IV"
			num_integer -= 4
		end
		
		while (num_integer > 0)
			roman_numeral_string += "I"
			num_integer -= 1
		end

		return roman_numeral_string

	end
end

#is this called monkeypatching?

#This is not a permanent change to the Fixnum class. It seems that this this new method that is being added will only occur at runtime.
