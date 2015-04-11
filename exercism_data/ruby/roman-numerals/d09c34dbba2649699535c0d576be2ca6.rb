class Fixnum
	def to_roman
		number = self
		result = String.new

		until number <= 0 do
			if number >= 1000
				number -= 1000
				result << "M"
				next
			end

			if number % 1000 >= 900
				number -= 900
				result << "CM"
				next
			end

			if number % 1000 >= 500
				number -= 500
				result << "D"
				next
			end

			if number % 1000 >= 400
				number -= 400
				result << "CD"
				next
			end

			if number % 1000 >= 100
				number -= 100
				result << "C"
				next
			end

			if number % 100 >= 90
				number -= 90
				result << "XC"
				next
			end

			if number % 100 >= 50
				number -= 50
				result << "L"
				next
			end

			if number % 100 >= 40
				number -= 40
				result << "XL"
				next
			end

			if number >= 10
				number -= 10
				result << "X"
				next
			end

			if number % 10 == 9
				number -= 9
				result << "IX"
				next
			end

			if number % 10 == 4
				number -= 4
				result << "IV"
				next
			end

			if number >= 5
				number -= 5
				result << "V"
				next
			end

			if number >= 1
				number -= 1
				result << "I"
				next
			end
		end

		result
	end
end
