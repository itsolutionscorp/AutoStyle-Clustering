class Year
  def self.leap? year
    traditional_leap_year?(year) && special_leap_year?(year)
  end

  def traditional_leap_year? year
    year % 4 == 0
  end

  def self.special_leap_year? year
    return true if year % 400 == 0
    return false if year % 100 == 0
    true
  end
end
