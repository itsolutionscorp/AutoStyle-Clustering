module LeapYear
  def leap?(year)
    divisible_by?(4, year) &&
      (!divisible_by?(100, year) || divisible_by?(400, year))
  end

  private

  def divisible_by?(n, year)
    year % n == 0
  end
end

class Year
  extend LeapYear
end
