class Year
  def self.leap?(year)
   year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? true : false
  end
end
