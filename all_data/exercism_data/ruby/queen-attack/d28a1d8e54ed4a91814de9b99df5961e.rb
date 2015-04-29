class Queens
  attr_reader :board, :white, :black

  def initialize(initial = { white: [0, 3], black: [7, 3] })
    @white = initial[:white]
    @black = initial[:black]

    fail ArgumentError if white == black

    @board =  ChessBoard.new.place_item(white, 'W').place_item(black, 'B')
  end

  def attack?
    white[0] == black[0] || white[1] == black[1] ||
    (white[0] - black[0]).abs == (white[1] - black[1]).abs
  end

  def to_s
    @board.to_s
  end
end

module GameBoard
  attr_reader :board, :separator

  def initialize(empty_char = 'O', separator = ' ')
    row = (empty_char * self.class::COLUMNS).chars
    @board = self.class::ROWS.times.reduce([]) { |a, _| a << row.dup }
    @separator = separator
  end

  def place_item(position, char)
    x, y = position
    board[x][y] = char
    self
  end

  def to_s
    board.map { |r| r.join(separator) }.join("\n")
  end
end

class ChessBoard
  COLUMNS = 8
  ROWS = 8

  include GameBoard
end
