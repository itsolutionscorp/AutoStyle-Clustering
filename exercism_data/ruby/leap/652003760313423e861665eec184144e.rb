module Year
  def self.leap?(year)
    return true if year % 400 == 0
    year % 4 == 0 and year % 100 != 0
  end
end
