class Year
  def self.leap?(year)
    # if the year is evenly divisible by 400 then it already
    # meets the criteria of being evenly divisible by 4 and 100
    if year % 400 == 0
      true
    # failed on evenly divisible by 400 but could still be
    # evenly divisible by 100
    elsif year % 100 == 0
      false
    # not one of the goofy century cases so check for a normal
    # leap year
    elsif year % 4 == 0
      true
    # not a leap year
    else
      false
    end
  end
end
