class Year
  def self.leap?(year)
    year % 4 == 0 and ((year % 100 == 0) == (year % 400 == 0))
  end
end
