class Year
  def self.leap?(year)
    year % 100 != 0 || (year % 100 == 0 && year % 400 == 0) if year % 4 == 0
  end
end
