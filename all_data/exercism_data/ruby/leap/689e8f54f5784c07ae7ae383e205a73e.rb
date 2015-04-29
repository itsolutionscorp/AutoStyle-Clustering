module Year
  extend self

  def leap?(year)
    return false if skip_year?(year)
    year % 4 == 0
  end

  private

  def skip_year?(year)
    return false  if evenly_divisible?(year, 400)
    return true   if evenly_divisible?(year, 100)
  end

  def evenly_divisible?(value, num)
    value % num == 0
  end

end
