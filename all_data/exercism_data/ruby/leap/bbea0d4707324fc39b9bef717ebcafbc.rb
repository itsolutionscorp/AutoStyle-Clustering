class Year
  
  def self.leap?(year)
    leap = { yes: "Yes, #{year} is a leap year",
              no: "No, #{year} is not a learp year" }

    if year % 4 != 0
      leap[:no]
    elsif year % 100 != 0
      leap[:yes]
    elsif year % 400 != 0
      leap[:no]
    else
      leap[:yes]
    end
  end
end
