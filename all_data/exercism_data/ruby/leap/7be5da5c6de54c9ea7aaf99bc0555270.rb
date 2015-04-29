class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    regular_leap_year? or exceptional_century?
  end

  private

  def regular_leap_year?
    divisible_by?(4) and !divisible_by?(100)
  end

  def exceptional_century?
    divisible_by?(400)
  end

  def divisible_by?(number)
    year.modulo(number).zero?
  end
end
