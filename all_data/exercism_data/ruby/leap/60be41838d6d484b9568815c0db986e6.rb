class Year
  def self.leap?(year)
    # Years divisible by 400 are leap years.
    return true if year % 400 == 0

    # Years divisible by 100 (and not by 400) are not leap years.
    return false if year % 100 == 0

    # Otherwise, years divisible by 4 are leap years.
    return year % 4 == 0
  end
end
