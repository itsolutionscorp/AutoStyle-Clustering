class Year
  def self.leap?(year)
    if year % 400 == 0 and year % 100 == 0
      return true
    end
    if year % 4 == 0 and year % 100 != 0
      return true
    end
    return false
  end
end
