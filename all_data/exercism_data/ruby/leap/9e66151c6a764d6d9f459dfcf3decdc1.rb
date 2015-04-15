class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    non_century_leap_year? || fourth_century_leap_year?
  end

  private

  def non_century_leap_year?
    simple_leap_year? && !lands_on_a_century?
  end

  def fourth_century_leap_year?
    simple_leap_year?  && lands_on_a_fourth_century?
  end

  def simple_leap_year?
    divisible_by?(4)
  end

  def lands_on_a_century?
    divisible_by?(100)
  end

  def lands_on_a_fourth_century?
    divisible_by?(400)
  end

  def divisible_by?(number)
    @year % number == 0
  end
end
