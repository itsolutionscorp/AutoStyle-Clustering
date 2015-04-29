class Grains
  SQUARES = 64
  def initialize() end
  def square(input)
    2 ** (input - 1)
  end
  def total()
    running_sum = 0
    nth_power = 1
    SQUARES.times do
      running_sum += nth_power
      nth_power *= 2
    end
    running_sum
  end
end
