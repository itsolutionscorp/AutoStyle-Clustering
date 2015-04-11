class Year
  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by? 4 and (not_divisible_by? 100 or divisible_by? 400)
  end

  def divisible_by?(number)
    @year % number == 0
  end

  def not_divisible_by?(number)
    not divisible_by? number
  end
end
