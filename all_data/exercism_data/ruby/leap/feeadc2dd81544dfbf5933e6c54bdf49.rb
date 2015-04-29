class Year
  def initialize year
    @year = year
  end

  def leap?
    return false if @year%100==0 and not @year%400==0
    return true if @year%4==0
    false
  end

  def leap?
    return false if mod_zero?(100) and not mod_zero?(400)
    return true if mod_zero? 4
    false
  end

  def leap?
    mod_zero?(400) or non_century?
  end

  def non_century?
    mod_zero?(4) and not mod_zero?(100)
  end

  def mod_zero? divisor
    @year % divisor == 0
  end
end
