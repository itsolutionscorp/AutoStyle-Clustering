class Year

  def self.leap?(year)
    leapyear = year % 4 ==0
    leapyear = false if (year % 100 == 0) && (year % 400 != 0)
    leapyear
  end

end
