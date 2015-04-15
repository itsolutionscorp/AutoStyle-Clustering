class Year

  def initialize(year)
    @year = year
  end

  def leap?
    four_hundred.zero? || (four.zero? unless one_hundred.zero?)
  end

  def four_hundred
    @year % 400
  end

  def one_hundred
    @year % 100
  end

  def four
    @year % 4
  end

end
