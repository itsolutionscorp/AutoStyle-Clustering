class Year
  attr_reader :year_number

  def initialize(year_number)
    @year_number = year_number
  end

  def leap?
    divisible_by_four? and not exempt_century?
  end

  def divisible_by_four?
    year_number % 4 == 0 
  end

  def exempt_century?
    century? and not leap_century?
  end

  def century?
    year_number % 100 == 0 
  end

  def leap_century?
    year_number % 400 == 0
  end

end
