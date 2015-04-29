class Fixnum
  def divisible_by?(num)
    (self % num) == 0
  end
end

class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (divisible_by? 4 and not divisible_by? 100) or divisible_by? 400
  end

  private

  def divisible_by?(num)
    @year.divisible_by? num
  end
end
