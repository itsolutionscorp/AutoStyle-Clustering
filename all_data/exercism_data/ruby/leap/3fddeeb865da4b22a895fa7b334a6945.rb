class Year
  def self.leap?(year)
    if year % 100 == 0 && year % 400 != 0 || year % 4 != 0
      leap = false
    else
      leap = true
    end
  end
end
