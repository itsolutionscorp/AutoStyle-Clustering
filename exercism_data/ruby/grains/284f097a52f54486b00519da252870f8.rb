class Grains
  attr_accessor :board

  def initialize
    @board = [1]
  end

  def square(x)
    board[x-1] ||= square(x-1) * 2
  end

  def total
    square(64)
    board.inject(&:+)
  end
end
