class Year
  def self.leap?(year)
    if year % 400 == 0
      true
    else
      (year % 4 == 0) and !(year % 100 == 0)
    end
  end
end
