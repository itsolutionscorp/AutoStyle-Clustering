class Grains
  def initialize
    @board = (0..63).map { |i| 1 << i }
  end

  def square(n)
    @board[n-1]
  end

  def total
    @board.inject(:+)
  end
end
