class Grains
  def square(position)
    2 ** (position - 1)
  end

  def total
    (1..64).map { |num| square(num) }.reduce(:+)
  end
end
