class Chessboard
  def initialize
    @board = Hash.new
    (1..64).each { |i| @board[i] = 2 ** (i-1) }
  end

  def square(n)
    @board[n]
  end

  def total
    @board.values.inject(:+)
  end
end

Grains = Chessboard
