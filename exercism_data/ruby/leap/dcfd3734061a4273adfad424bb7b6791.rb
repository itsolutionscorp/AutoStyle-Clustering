class Year
  def self.leap?(year)
    leap = year % 4 == 0 and year % 100 != 0 or year % 400 == 0
    leap ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year"
  end
end
