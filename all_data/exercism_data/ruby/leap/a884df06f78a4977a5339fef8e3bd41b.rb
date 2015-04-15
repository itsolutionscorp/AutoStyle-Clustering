class Year
  def self.leap? year
    return true if year % 400 == 0
    return false if year % 100 == 0
    if year % 4 == 0
      return true
    else
      false
    end
  end
end
