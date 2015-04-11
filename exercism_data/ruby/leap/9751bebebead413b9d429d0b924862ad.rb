class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if is_leap = divisible_by?(4)
      if divisible_by?(100) && !divisible_by?(400)
        is_leap = false
      end
    end

    is_leap
  end

  private

  def divisible_by?(number)
    year.modulo(number).zero?
  end

end
