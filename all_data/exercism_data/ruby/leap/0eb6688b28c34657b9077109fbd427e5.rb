class Year

  YEAR_DIVISORS = { 400 => :leap, 100 => :not_leap, 4 => :leap }

  def initialize year
    @year = year
  end

  def leap?
    leap_finder == :leap
  end


  private

  def leap_finder
    YEAR_DIVISORS.each { |divisor,leap_value | return leap_value if given_year_divisible_by?(divisor) }
  end

  def given_year_divisible_by?(amount)
    @year.modulo(amount).zero?
  end
end
