class Grains
  def initialize
  end

  def square(int)
    2 ** (int - 1)
  end

  def total
    (1..64).to_a.map { |num| square(num) }.inject(:+)
  end
end
