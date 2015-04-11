class Year
  def self.leap?(year)
    year = year.to_i
    (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)
  end
end
