class Year
  def initialize(year)
    @year = year
  end

  def leap?
    exceptional_century? || regular_leap_year?
  end

  private
  attr_accessor :year

  def fourth_year?
    year.modulo(4).zero?
  end

  def not_a_regular_century?
    !year.modulo(100).zero?
  end

  def exceptional_century?
    year.modulo(400).zero?
  end
  
  def regular_leap_year?
    fourth_year? && not_a_regular_century?
  end
end
