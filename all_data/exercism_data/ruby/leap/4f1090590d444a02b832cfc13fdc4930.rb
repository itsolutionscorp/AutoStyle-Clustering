class Year
  def self.leap?(year)
    if year % 100 == 0
      return false unless year % 400 == 0
    end
    return true if year % 4 == 0
    false
  end
end
