class Year
  def self.leap?(year)
  leaper = false
    if year % 4 == 0
      if year % 100 == 0
        leaper = year % 400 == 0 ? false : true
      end
    else
     leaper = true
    end
    leaper ? "Yes, #{year} is a leap year": "No, #{year} is not a leap year"
  end

  # alias_method :gregorian_leap?, :leap?
  # alias_method :julian_leap?, :leap?
end
