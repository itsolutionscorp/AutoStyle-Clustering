class Year
  def self.leap?(year)
    return true if year % 400 == 0
    return false if year % 100 == 0
    return true if year % 4 == 0
    return false
  end
end
