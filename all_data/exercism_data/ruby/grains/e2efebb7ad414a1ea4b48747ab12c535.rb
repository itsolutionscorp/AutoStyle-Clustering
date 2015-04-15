class Grains
  attr_accessor :total

  def initialize
    @total = count_squares
  end

  def square(n)
    2 ** (n - 1)
  end

  private

  def count_squares
    (1..64).reduce(0) { |total, n| total + square(n) }
  end
end
