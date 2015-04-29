class Year
  def self.leap?(year)
    new(year).leap?
  end

  def initialize(year)
    @year = year
  end

  def leap?
    leap_year? && !skipped_century?
  end

  private

  def leap_year?
    divisible_by? 4
  end

  def skipped_century?
    century? && !leap_century?
  end

  def century?
    divisible_by? 100
  end

  def leap_century?
    divisible_by? 400
  end

  def divisible_by?(number)
    @year % number == 0
  end
end
