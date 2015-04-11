class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if is_leap = divisible_by_4?
      if divisible_by_100? && !divisible_by_400?
        is_leap = false
      end
    end

    is_leap
  end

  private

  def divisible_by_100?
    year.modulo(100).zero?
  end

  def divisible_by_400?
    year.modulo(400).zero?
  end

  def divisible_by_4?
    year.modulo(4).zero?
  end
end
