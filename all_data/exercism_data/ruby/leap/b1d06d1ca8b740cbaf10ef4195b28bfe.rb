class Year
  def self.leap?(year)
    return false if year % 100 == 0 unless year % 400 == 0
    return true if year % 4 == 0
    false
  end
end
