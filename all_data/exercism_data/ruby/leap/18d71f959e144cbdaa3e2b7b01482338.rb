class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by_four? && not_break_centuries_rules?
  end

  private
  def divisible_by_four?
    year % 4 == 0
  end

  def not_divisible_by_hundred?
    year % 100 != 0
  end

  def divisible_by_four_hundrer?
    year % 400 == 0
  end

  def not_break_centuries_rules?
    not_divisible_by_hundred? || divisible_by_four_hundrer?
  end
end
