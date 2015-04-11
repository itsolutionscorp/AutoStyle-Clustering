class Year

  def initialize year
    @year = year
  end
  
  def leap?
    #divisible_by 4 and not divisible_by 100 or divisible_by 400

    return false unless divisible_by 4

    return true unless divisible_by 100

    return false unless divisible_by 400

    true

  end

  private 
  def divisible_by divisor
    @year % divisor == 0
  end

end
