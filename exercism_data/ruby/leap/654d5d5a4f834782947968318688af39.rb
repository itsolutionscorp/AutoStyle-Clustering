class Year

  attr_accessor :year

  def initialize year
    @year = year
  end

  def leap?
    if divisible_by 4
      return true if divisible_by 100 and divisible_by 400
      return false if divisible_by 100
      return true
    end
    return false
  end

  def divisible_by divisor
    @year % divisor == 0
  end

end
