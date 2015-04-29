module Year
  def self.leap?(year)
    return false if year % 4 > 0
    return false if year % 100 == 0 unless year % 400 == 0
    return true
  end
end
