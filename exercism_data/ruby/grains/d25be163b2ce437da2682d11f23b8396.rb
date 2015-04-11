class Grains
  attr_accessor :board

  def initialize
    @board = { 1 => 1}
  end

  def square(x)
    return board[x] if board.key x
    board[x] = square(x-1) * 2
  end

  def total
    square(64)
    board.values.inject(&:+)
  end
end
