class Year

  def self.leap?(year)
    year % 400 == 0 or (year % 4 == 0 && year % 100 != 0)
  end

end
