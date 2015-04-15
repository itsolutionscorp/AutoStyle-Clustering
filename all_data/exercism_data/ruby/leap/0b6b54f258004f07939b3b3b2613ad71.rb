class Year
  def initialize(year)
    @year = year.to_f
  end

  def leap?
    exceptional_leap_year? || ordinary_leap_year?
  end

  def exceptional_leap_year?
    year_is_evenly_divisible_by_400?
  end

  def ordinary_leap_year?
    return false if year_is_evenly_divisible_by_100?
    year_is_evenly_divisible_by_4?
  end

  def year_is_evenly_divisible_by_4?
    ((@year / 4) % 1) == 0.0
  end

  def year_is_evenly_divisible_by_100?
    ((@year / 100) % 1) == 0.0
  end

  def year_is_evenly_divisible_by_400?
    ((@year / 400) % 1) == 0.0
  end
end
