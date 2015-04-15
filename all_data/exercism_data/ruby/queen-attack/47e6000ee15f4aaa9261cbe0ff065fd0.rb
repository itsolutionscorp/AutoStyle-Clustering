class ChessBoard
  def initialize
    @board = 8.times.map { Array.new(8, "_") }
  end

  def graphics
    @board.map { |row| row.join(" ") }.join ("\n")
  end

  def place_symbol(position: [], symbol: "O")
    x, y = *position
    @board[x][y] = symbol
  end
end

class Queens
  DEFAULTS = { white: [0, 3], black: [7, 3] }

  attr_reader :white, :black

  def initialize(white: nil, black: nil)
    @white = white || DEFAULTS[:white]
    @black = black || DEFAULTS[:black]

    fail ArgumentError if coincident?
  end

  def to_s
    board = ChessBoard.new

    board.place_symbol(position: @white, symbol: "W")
    board.place_symbol(position: @black, symbol: "B")

    board.graphics
  end

  def attack?
    same_diagonal? || same_row? || same_column?
  end

  private

  def same_diagonal?
    (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end

  def same_column?
    @white[1] == @black[1]
  end

  def same_row?
    @white[0] == @black[0]
  end

  def coincident?
    @white == @black
  end
end
