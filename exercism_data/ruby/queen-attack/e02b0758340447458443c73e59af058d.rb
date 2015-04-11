class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    @white = white
    @black = black
    cannot_occupy_same_space
    cannot_be_positioned_outside_the_board
  end

  def to_s
    board = new_board

    place_white(board)
    place_black(board)

    board.map do |rows|
      rows.join(" ")
    end.join("\n")
  end

  def attack?
   same_row? || same_column? || diagonal?
  end

  private

  def cannot_occupy_same_space
    if black == white
      raise ArgumentError.new
    end
  end

  def cannot_be_positioned_outside_the_board
    if (black + white).any? { |i| i > 7 }
      raise ArgumentError.new
    end
  end

  def new_board
   board = Board.new
   board.new_board
  end

  def place_white(board)
    board[white.first][white.last] = 'W'
  end

  def place_black(board)
    board[black.first][black.last] = 'B'
  end

  def same_row?
    white.first == black.first
  end

  def same_column?
    white.last == black.last
  end

  def diagonal?
    (white.first + black.last) == (white.last + black.first) ||
    (white.first + black.first) == (white.last + black.last)
  end
end

class Board
  def new_board
    Array.new(8) {Array.new(8,'O')}
  end
end
