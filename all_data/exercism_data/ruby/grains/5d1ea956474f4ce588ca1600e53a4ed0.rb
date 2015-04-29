class Grains
  SQUARES = 64
  def initialize() end
  def calc_nth_power(n)
    2 ** n
  end
  def square(input)
    calc_nth_power(input - 1)
  end
  def total()
    SQUARES.times.map { |i| calc_nth_power(i) }.reduce(:+)
  end
end
