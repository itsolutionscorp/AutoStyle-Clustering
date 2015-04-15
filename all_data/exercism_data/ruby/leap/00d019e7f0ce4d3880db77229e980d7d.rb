class Year
  def self.leap?(year)
    # Years divisible by 400 are leap years.
    return true if year % 400 == 0

    # Years divisible by 4 are leap years if they aren't divisible by 100.
    return true if (year % 4 == 0) && (year % 100 != 0)

    # Other years are not leap years
    return false
  end
end
