class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (vanilla_leap_year? && !century?) || exceptional_century?
  end

  private
  def vanilla_leap_year?
    multiple_of? 4
  end

  def century?
    multiple_of? 100
  end

  def exceptional_century?
    multiple_of? 400
  end

  def multiple_of? n
    @year % n == 0
  end
end
