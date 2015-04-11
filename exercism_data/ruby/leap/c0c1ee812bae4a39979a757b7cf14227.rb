class Year

#on every year that is evenly divisible by 4
#except every year that is evenly divisible by 100
#unless the year is also evenly divisible by 400
  def self.leap? (year)
    is_leap = false

    if year % 4 === 0
      is_leap = true
      if year % 100 === 0
        is_leap = false unless year % 400 === 0
      end
    end

    is_leap
  end
end
