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
    false if @year % 4 != 0
    true if @year % 100 != 0 || @year % 400 == 0
  end

end
