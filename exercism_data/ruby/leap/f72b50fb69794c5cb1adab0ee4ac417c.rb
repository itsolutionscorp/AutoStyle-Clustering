class Year
  def initialize(year)
    @year = YearNumber.new year
  end

  def leap?
    @year.divisible_by? 4 and
    (@year.not_divisible_by? 100 or @year.divisible_by? 400)
  end
end

class YearNumber
  def initialize(year)
    @digits = year
  end

  def divisible_by?(number)
    @digits % number == 0
  end

  def not_divisible_by?(number)
    not divisible_by? number
  end
end
