class Year
  def self.leap?(year)
    if year % 4 == 0 and year % 100 != 0 || year % 400 == 0
      return true, '#{year} is a leap year!'
    else 
      return false
    end
  end
end
