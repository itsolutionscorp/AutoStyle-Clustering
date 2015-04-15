# queens.rb
class Queens
  attr_reader :white, :black, :board

  def initialize(white: [0, 3], black: [7, 3])
    fail ArgumentError if white == black
    @white = white
    @black = black
    @board = ([['_'] * 8] * 8).map(&:clone)
    @board[white[0]][white[1]] = 'W'
    @board[black[0]][black[1]] = 'B'
  end

  def to_s
    board.map { |row| row.join(' ') }.join("\n")
  end

  def attack?
    same_row? || same_col? || diagonal?
  end

  def same_row?
    white[0] == black[0]
  end

  def same_col?
    white[1] == black[1]
  end

  def diagonal?
    (white[0] - black[0]).abs == (white[1] - black[1]).abs
  end
end
