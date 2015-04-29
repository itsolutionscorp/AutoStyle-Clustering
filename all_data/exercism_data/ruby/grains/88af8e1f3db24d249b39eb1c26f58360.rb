class Grains
  SQUARE_COUNT = 64

  def square(number)
    2 ** (number-1)
  end

  def total
    (1..SQUARE_COUNT).reduce(0) { |sum, number| sum + square(number) }
  end
end
