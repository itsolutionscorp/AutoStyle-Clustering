class Year
  
  # Determine whether a given year is a leap year
  #
  # year - an integer representing the year to check
  #
  # A year is a leap year if it is divisible by 4, but not 100 (but it's a leap
  # year again if it's divisible by 400. Completely nuts!)
  def self.leap?(year)
    if (year % 400) == 0
      true
    elsif (year % 100) == 0
      false
    elsif (year % 4) == 0
      true
    else
      false
    end
  end
  
end
