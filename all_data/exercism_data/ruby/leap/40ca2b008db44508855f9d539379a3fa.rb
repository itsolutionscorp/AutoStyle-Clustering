class Year
  def initialize(year)
    @year = year
  end

  def leap?
    [ LeapYearChecker.new(400,is_leap_year),
      LeapYearChecker.new(100,is_not_leap_year),
      LeapYearChecker.new(4,is_leap_year),
      LeapYearChecker.new(1,is_not_leap_year)
    ].find{ |special_year| special_year.match?(@year) }.execute
  end

  def is_leap_year
    true
  end

  def is_not_leap_year
    false
  end
end

class LeapYearChecker
  def initialize(mod_value,leapness)
    @mod_value = mod_value
    @leapness = leapness
  end

  def match?(year)
    year % @mod_value == 0
  end

  def execute
    return @leapness
  end
end
