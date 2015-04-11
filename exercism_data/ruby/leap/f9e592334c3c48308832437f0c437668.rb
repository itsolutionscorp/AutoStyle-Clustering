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
    divisible_by_four? and !divisible_by_one_hundred?
  end

  def exceptional_century?
    divisible_by_four_hundred?
  end

  def divisible_by_four?
    divisible_by?(4)
  end

  def divisible_by_one_hundred?
    divisible_by?(100)
  end

  def divisible_by_four_hundred?
    divisible_by?(400)
  end

  def divisible_by?(number)
    year.modulo(number).zero?
  end
end
