class Year
  def self.leap?(year)
    # Leap Years occur when a year is divisble by 400
    return true if (year % 400 == 0)

    # Learp years occur when a year is divisble by 4, but
    # not when it's also divisble by 100
    return true if (year % 4 == 0 && !(year % 100 == 0))

    return false
  end
end
