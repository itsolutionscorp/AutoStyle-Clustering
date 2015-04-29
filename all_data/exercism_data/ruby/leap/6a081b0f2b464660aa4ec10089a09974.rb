module Year
  def self.leap?(year)
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
      true
    else
      false
    end
  end
end
