class Year
  def initialize(year)
    @year = year
  end

  # Occurs every four years
  # except when it's also evenly divisible by 100 and not 400
  def leap?
    @year % 4 == 0 and not_100_year_rule?
  end

  private
  def not_100_year_rule?
    ! (@year % 100 == 0 && @year % 400 != 0)
  end
end
