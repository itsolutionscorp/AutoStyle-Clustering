class Year
  # leap?
  # Determine if a given year is a leap year
  #
  # Algorithm:
  #  on every year that is evenly divisible by 4
  #   except every year that is evenly divisible by 100
  #    unless the year is also evenly divisible by 400
  #
  def self.leap?(year)
    return true if (0 == year % 4 && (0 != year % 100 || 0 == year % 400))
    false
  end
end
