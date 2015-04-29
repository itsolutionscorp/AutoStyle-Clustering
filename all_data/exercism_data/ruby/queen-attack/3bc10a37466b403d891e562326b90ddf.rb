class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3], board: Board.new)
    @white = white
    @black = black
    @board = board
    cannot_occupy_same_space
    cannot_be_positioned_outside_the_board
  end

  def to_s
    board.new_board

    board.place_piece(white, 'W')
    board.place_piece(black, 'B')

    board.to_s
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  private

  attr_reader :board

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

  def same_row?
    white.first == black.first
  end

  def same_column?
    white.last == black.last
  end

  def diagonal?
    x_value == y_value
  end

  def x_value
    (white.first - black.first).abs
  end

  def y_value
    (white.last - black.last).abs
  end
end

class Board
  attr_reader :new_board

  def initialize
    @new_board = Array.new(8) {Array.new(8,'O')}
  end

  def place_piece(position, name)
    new_board[position.first][position.last] = name
  end

  def to_s
    new_board.map do |rows|
      rows.join(" ")
    end.join("\n")
  end
end
