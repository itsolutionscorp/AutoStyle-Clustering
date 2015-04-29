class Year

  def initialize(year)
    @year = year
  end

  def leap?
    leap_algorithm
  end

  def divisible?(num)
    true if @year % num == 0
  end

  def leap_algorithm
   divisible?(400) || (divisible?(4) && !divisible?(100)) 
  end

end
