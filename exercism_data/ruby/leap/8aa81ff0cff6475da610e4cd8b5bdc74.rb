module Year
  extend self

  def leap?(year)
    return false if skip_leap_year?(year)
    evenly_divisible?(year, 4)
  end

  private

  def skip_leap_year?(year)
    evenly_divisible?(year, 100) && !evenly_divisible?(year, 400)
  end

  def evenly_divisible?(year, num)
    year % num == 0
  end

end
