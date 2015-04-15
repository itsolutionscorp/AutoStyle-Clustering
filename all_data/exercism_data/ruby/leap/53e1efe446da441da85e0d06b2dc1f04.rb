class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (vanilla? && !century?) || exceptional_century?
  end

  def vanilla?
    (@year % 4) == 0
  end

  def century?
    (@year % 100) == 0
  end

  def exceptional_century?
    (@year % 400) == 0
  end
end
