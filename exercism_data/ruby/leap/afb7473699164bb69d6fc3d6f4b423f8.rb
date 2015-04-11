class Year
  def self.leap? year
    traditional_leap_year?(year) && not_special_leap_year?(year)
  end

  def self.traditional_leap_year? year
    year % 4 == 0
  end

  def self.not_special_leap_year? year
    return true if year % 400 == 0
    return false if year % 100 == 0
    true
  end
end
