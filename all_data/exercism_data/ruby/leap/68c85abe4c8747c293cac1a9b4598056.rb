class Fixnum
  def divisible?(num)
    (self % num) == 0
  end
end

class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (@year.divisible? 4 and not @year.divisible? 100) or @year.divisible? 400
  end
end
