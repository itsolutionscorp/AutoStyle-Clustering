class Year
	def initialize(year)
		@year = year
	end

	def base_case?
		divisible_by? 4
	end

	def divisible_by?(num)
		@year % num == 0
	end

	def leap?
		if divisible_by? 100
			if divisible_by? 400
				base_case?
			end
		else
			base_case?
		end
	end
end
