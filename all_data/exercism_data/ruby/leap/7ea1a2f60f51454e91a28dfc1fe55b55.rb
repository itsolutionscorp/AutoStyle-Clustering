class Year
  def self.leap?(year)
    year % 4 == 0 && !leap_year_exception?(year)
  end

  private

  def self.leap_year_exception?(year)
    year % 100 == 0 && year % 400 != 0
  end
end
