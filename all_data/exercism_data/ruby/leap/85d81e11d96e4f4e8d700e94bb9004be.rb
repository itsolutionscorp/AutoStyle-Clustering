class Year

  def initialize year
    @year = year
  end
  
  def leap?
    #divisible_by 4 and not divisible_by 100 or divisible_by 400

    return divisible_by 4 unless divisible_by 100

    return (not divisible_by 100) unless divisible_by 400

    true

  end

  private 
  def divisible_by divisor
    @year % divisor == 0
  end

end
