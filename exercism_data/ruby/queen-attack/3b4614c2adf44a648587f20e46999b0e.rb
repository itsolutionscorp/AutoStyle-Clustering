require 'debugger'
class Queens
  attr_reader :white
  attr_reader :black

  BOARD = <<-BOARD.chomp
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
_ _ _ _ _ _ _ _
BOARD

  def initialize(opts = {})
    @white = opts[:white].nil? ? [0, 3] : opts[:white]
    @black = opts[:black].nil? ? [7, 3] : opts[:black]

    fail ArgumentError if @white == @black
  end

  def to_s
    board = BOARD.dup

    board[16 * @white[0] + @white[1] * 2] = 'W'
    board[16 * @black[0] + @black[1] * 2] = 'B'

    board
  end

  def attack?
    (@white - @black).length == 1 || ((@white[1] - @black[1]) / (@white[0] - @black[0])).abs == 1
  end
end
