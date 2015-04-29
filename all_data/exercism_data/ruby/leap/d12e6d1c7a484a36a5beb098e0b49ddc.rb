class Year
  def self.leap?(year)
    if check_leap(year)
      "Yes, #{year} is a leap year"
    else
      "No, #{year} is not a leap year"
    end
  end

  def self.check_leap(year)
    leap = true
    if year % 100 == 0
      leap = false unless year % 400 == 0
    elsif year % 4 == 0
      leap = true
    else
      leap = false
    end
    leap
  end
end
