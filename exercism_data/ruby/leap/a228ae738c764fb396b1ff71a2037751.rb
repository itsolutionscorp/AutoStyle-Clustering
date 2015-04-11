class Year
  def initialize(year)
    @year = year
  end

  def leap?
    div4? && ( div400? || not_div100? )
  end

  def div4?
    @year % 4 == 0
  end

  def div400?
    @year % 400 == 0
  end

  def not_div100?
    @year % 100 != 0
  end
end
