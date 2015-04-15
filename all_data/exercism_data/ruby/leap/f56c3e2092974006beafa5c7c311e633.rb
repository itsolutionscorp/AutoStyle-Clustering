class Year

	def initialize(year)
		@year = year
	end

	def leap?
		every_4_years(@year) && every_100_years(@year) || every_400_years(@year)
	end
	
  def every_4_years(year)
    year % 4 == 0
  end

  def every_100_years(year)
    year % 100 != 0
  end

  def every_400_years(year)
    year % 400 == 0
  end

end
