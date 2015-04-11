class Grains
  SQUARES = 64
  def initialize() end
  def square(input)
    2 ** (input - 1)
  end
  def total()
    SQUARES.times.map { |i| 2**i }.reduce(:+)
  end
end
