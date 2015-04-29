class Grains

  def square(index)
    2 ** (index - 1)
  end

  def total
    (1..64).reduce(0) { |sum, index| sum + square(index) }
  end

end
