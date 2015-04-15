class Year
  def self.leap? year
    leap_year = LeapYear.new(year)
    leap_year.leap?
  end
end

class LeapYear

  def initialize year
    @year = year
  end

  def leap?
    false if divisible_by?(4)
    true if !divisible_by?(100) || divisible_by?(400)
  end

  def divisible_by? divisor
    @year % divisor == 0
  end

end
