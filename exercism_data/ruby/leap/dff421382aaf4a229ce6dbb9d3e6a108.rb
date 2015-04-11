class Year
  def self.leap?(year)
    return false if leap_year_exception?(year)
    year % 4 == 0
  end

  def self.leap_year_exception?(year)
    year % 100 == 0 && year % 400 != 0
  end
end
