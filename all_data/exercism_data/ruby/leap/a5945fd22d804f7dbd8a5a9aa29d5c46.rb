class Year
  
  def initialize(year)
    @leap = (year % 4 == 0 && (year % 100 > 0 || year % 400 == 0))
  end

  def leap?
    @leap
  end

end
