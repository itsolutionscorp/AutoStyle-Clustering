class Grains
  def initialize(squares = 64)
    @squares = squares
  end

  attr_reader :squares

  def square(n)
    2**(n - 1)
  end

  def total
    (1..squares).inject(0) { |sum, n| sum + square(n) }
  end

  # Bonus

  def total_speed
    # Sum of a geometric series
    2 ** squares - 1
  end
end
