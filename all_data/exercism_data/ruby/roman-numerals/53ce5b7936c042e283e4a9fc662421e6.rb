#! 
class Fixnum

	def to_roman

		numerals_array = ['I', 'V', 'X', 'L', 'C', 'D', 'M']


		power = case self
		when self % 1000 == 0
			6

		when self % 500 == 0
			5

		when self % 100 == 0
			4

		when self % 50 == 0
			3

		when self % 10 == 0
			2

		when self % 5 == 0
			1

		when self % 1 == 0
			0

		end
		puts power

	end

end

puts 5.to_roman
