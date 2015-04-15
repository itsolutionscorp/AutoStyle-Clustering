module Year
  extend self

  def leap?(year)
    return true  if evenly_divisible?(year, 400)
    evenly_divisible?(year, 4) && !evenly_divisible?(year, 100)
  end

  private

  def evenly_divisible?(year, num)
    year % num == 0
  end

end
