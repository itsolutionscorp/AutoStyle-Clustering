class Year

  def initialize year
    @year = year
  end

  def leap?
    leap_year? unless (century? && ! exceptional_century?)
  end

  private

  def divisible_by? years
    @year.modulo(years).zero?
  end 

  def leap_year?
  	divisible_by? 4
  end

  def century?
  	divisible_by? 100
  end

  def exceptional_century?
  	divisible_by? 400
  end

end
