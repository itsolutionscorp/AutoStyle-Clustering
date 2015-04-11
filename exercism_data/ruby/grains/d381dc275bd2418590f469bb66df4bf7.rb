class Grains

  def square(n)
    2**(n - 1)
  end

  def total
    board = (1..64)
    all_squares = board.map {|n| 2**(n - 1)}
    all_squares.inject(:+)
  end
end
