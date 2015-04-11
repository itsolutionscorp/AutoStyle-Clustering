class Year

	def initialize(year)
		@year = year
	end

	def leap?
		every4(@year) && every100(@year) || every400(@year)
	end
	
  def every4(year)
    year % 4 == 0
  end

  def every100(year)
    year % 100 != 0
  end

  def every400(year)
    year % 400 == 0
  end

end
