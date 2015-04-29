class Year
  def self.leap?(year)
    if year % 4 == 0
      return false if (year % 100 == 0 && year % 400 != 0)
      return true
    end
  end
end
