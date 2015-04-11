class Year
	def initialize year
		if not year.is_a?Integer
			raise ArgumentError, "value is not a valid year"
		end
		@year = year
	end

	def leap?
		return ((@year % 4 == 0) and (@year % 100 != 0 or @year % 400 == 0))
	end
end
