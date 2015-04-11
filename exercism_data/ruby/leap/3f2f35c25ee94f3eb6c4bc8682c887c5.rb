class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    divisible_by?(4) && !century_to_skip?
  end

  private

  def century_to_skip?
    divisible_by?(100) && !divisible_by?(400)
  end

  def divisible_by?(number)
    year.modulo(number).zero?
  end

end
