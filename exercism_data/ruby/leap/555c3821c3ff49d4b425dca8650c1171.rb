class Year

  def initialize year
    @year = year
  end

  def leap?
    cent ? quadcent : quad
  end

  def cent
    @year % 100 == 0
  end

  def quadcent
    @year % 400 == 0
  end

  def quad
    @year % 4 == 0
  end


end
