class Year
  LEAP_YEAR = 4
  CENTURY = 100
  CENTURY_EXCEPTION = 400

  def initialize(year)
    @year = year
  end

  def leap?
    leap_year?(LEAP_YEAR) ? exception? : false
  end

  private

  attr_reader :year

  def exception?
    leap_year?(CENTURY) ? leap_year?(CENTURY_EXCEPTION) : true
  end

  def leap_year?(type)
    year % type == 0
  end
end
