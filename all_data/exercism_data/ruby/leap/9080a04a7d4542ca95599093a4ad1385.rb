class Year
  attr_reader :year

  def initialize(year)
    @year = year.to_i
  end

  def leap?
    is_leap = evenly_divisible_by?(4)
    is_leap = false if evenly_divisible_by?(100) unless evenly_divisible_by?(400)

    is_leap
  end

  private

  def evenly_divisible_by?(num)
    year % num == 0
  end
end
