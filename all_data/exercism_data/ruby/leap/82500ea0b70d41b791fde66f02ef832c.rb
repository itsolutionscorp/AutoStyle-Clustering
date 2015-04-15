class Year

  def initialize(year)
    @year = year
  end

  def leap?
    @year.divisible_by?(4) && !leap_exception?
  end

  private

  def leap_exception?
    @year.divisible_by?(100) && !@year.divisible_by?(400)
  end

end

class Fixnum

  # yeah, this still feels dirty

  def leap_year?
    Year.new(self).leap?
  end

  def divisible_by? number
    self % number == 0
  end

end
