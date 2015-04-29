require 'date'
class Year
	attr_accessor :year

	def initialize(year)
		@year = year
	end

	def leap?
		divisible_by_four && !divisible_by_100 || divisible_by_400
	end

	def divisible_by_four
		true if year % 4 == 0
	end

	def divisible_by_100
		true if year % 100 == 0
	end

	def divisible_by_400
		true if year % 400 == 0
	end



end
