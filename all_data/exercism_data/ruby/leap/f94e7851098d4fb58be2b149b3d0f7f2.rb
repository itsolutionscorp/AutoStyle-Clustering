class Year

	def self.leap?(year)
		(divisible_by_four?(year) && !divisible_by_one_hundred(year)) || divisible_by_four_hundred(year)
	end


private
	def self.divisible_by_four?(year)
		year % 4 == 0
	end

	def self.divisible_by_one_hundred(year)
		year % 100 == 0
	end

	def self.divisible_by_four_hundred(year)
		year % 400 == 0
	end

end
