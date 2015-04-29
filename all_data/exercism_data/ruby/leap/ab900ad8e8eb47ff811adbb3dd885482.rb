class Year
  def self.leap?(year)
    leap = true
    if year % 100 == 0
      leap = false unless year % 400 == 0
    end
    leap = false unless year % 4 == 0
    leap
  end
end
