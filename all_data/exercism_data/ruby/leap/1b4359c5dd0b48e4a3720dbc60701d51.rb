class Year
  def self.leap?(year)
    leap = false
    if year % 4 == 0
      leap = true
      if year % 100 == 0
        leap = false
        if year % 400 == 0
          leap = true
        end
      end
    end
    leap ? "Yes, #{year} is a leap year" : "No, #{year} is not a leap year"
  end
end

puts Year.leap?(1996)
