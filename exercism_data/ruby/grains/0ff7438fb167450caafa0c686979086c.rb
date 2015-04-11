class Grains

  def square(n)
    2 ** (n - 1)
  end

  def total
    board = (1..64).collect { |n| square(n) }
    board.sum
  end

end

class Array
  def sum
    self.inject{ |sum, x| sum + x }
  end
end
