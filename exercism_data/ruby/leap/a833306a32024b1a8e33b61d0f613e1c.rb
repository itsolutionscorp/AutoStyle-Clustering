class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    (vanilla_leap_year? && !century?) || exceptional_century?
  end

private
  def vanilla_leap_year?
    year_divisible_by(4)
  end

  def century?
    year_divisible_by(100)
  end

  def exceptional_century?
    year_divisible_by(400)
  end

  def year_divisible_by(num)
    year % num == 0
  end
end
