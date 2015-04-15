class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (@year % 400 == 0) || (@year % 4 == 0 && @year % 100 != 0)
  end

  def old_leap?
    return true if (@year % 400 == 0)
    return true if (@year % 4 == 0)
    return false if (@year % 100 == 0)
  end

  def very_old_leap?
    #on every year that is evenly divisible by 4
    if (@year % 4 == 0)
      #except every year that is evenly divisible by 100
      if (@year % 100 == 0)
        #unless the year is also evenly divisible by 400
        if (@year % 400 == 0)
          true
        else
          false
        end
      else
        true
      end
    else
      false
    end
  end

end
