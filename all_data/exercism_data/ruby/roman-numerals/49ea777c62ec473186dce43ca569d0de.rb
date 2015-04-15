class Integer
	def to_roman
		num = self
		roman_string = ""
		while num >= 1000
			num = num - 1000
			roman_string << "M"
		end

		if num >= 900
			num = num - 900
			roman_string << "CM"
		end

		while num >= 500
			num = num - 500
			roman_string << "D"
		end

		if num >= 400
			num = num - 400
			roman_string << "CD"
		end

		while num >= 100
			num = num - 100
			roman_string << "C"
		end

		if num >= 90
			num = num - 90
			roman_string << "XC"
		end

		while num >= 50
			num = num - 50
			roman_string << "L"
		end

		if num >= 40
			num = num - 40
			roman_string << "XL"
		end

		while num >= 10
			num = num - 10
			roman_string << "X"
		end

		if num >= 9
			num = num - 9
			roman_string << "IX"
		end

		if num >= 5
			num = num - 5
			roman_string << "V"
		end

		if num >= 4
			num = num - 4
			roman_string << "IV"
		end

		while num >= 1
			num = num - 1
			roman_string << "I"
		end

		roman_string
	end
end
