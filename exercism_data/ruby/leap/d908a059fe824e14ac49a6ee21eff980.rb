class Year

  def initialize(year)
    @year = year
  end

  def leap?
    fourth_year? unless century? and not fourth_century?
  end

  def fourth_year?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def fourth_century?
    @year % 400 == 0
  end

end
