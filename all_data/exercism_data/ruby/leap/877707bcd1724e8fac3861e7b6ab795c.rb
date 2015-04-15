class Year

  def initialize year
    @year = year
  end
  
  def divisible_by? year, years
    year.modulo(years).zero?
  end

  def leap?
    divisible_by?(@year, 400) ||
   (divisible_by?(@year, 4) unless 
    divisible_by?(@year, 100))
  end 

end
