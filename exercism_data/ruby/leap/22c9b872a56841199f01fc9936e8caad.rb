class Year

  def initialize(year)
    @year = year
  end

  def leap?
    exceptional_year? || year_divisible_by_4? && !century_year?
  end

  def divisible?(num)
    @year % num == 0
  end

  def year_divisible_by_4?
    divisible?(4)
  end

  def century_year?
    divisible?(100)
  end

  def exceptional_year?
    divisible?(400)
  end

end
