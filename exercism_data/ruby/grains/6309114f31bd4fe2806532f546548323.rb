class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    squares = (1..64).map(&method(:square))
    squares.inject(:+)
  end
end
