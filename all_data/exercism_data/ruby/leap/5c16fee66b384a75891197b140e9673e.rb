class Year
  def self.leap?(year)
    return (year % 4 == 0 ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year") unless year % 100 == 0
    return (year % 400 == 0 ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year")
  end
end

p Year.leap?(1997)
