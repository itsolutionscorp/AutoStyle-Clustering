class Year

  attr_accessor :year

  def initialize year
    @year = year
  end

  def leap?
    if divisible_by? 4
      return true if century? and quad_century?
      return false if century?
      return true
    end
    return false
  end

  def divisible_by? divisor
    @year % divisor == 0
  end

  def century?
    divisible_by? 100
  end

  def quad_century?
    divisible_by? 400
  end
end
