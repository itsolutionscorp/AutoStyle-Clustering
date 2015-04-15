class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    (year_divisible_by_four? && !century?) || year_divisible_by_four_hundred?
  end

  def year_divisible_by_four?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def year_divisible_by_four_hundred?
    year % 400 == 0
  end
  
end
