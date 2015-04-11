class Queens

  STARTING_WHITE_POSITION = [0, 3]
  STARTING_BLACK_POSITION = [7, 3]
  BOARD_SIZE = 8

  attr_reader :white, :black

  def initialize(white: STARTING_WHITE_POSITION, black: STARTING_BLACK_POSITION)
    raise ArgumentError if white == black
    @white = white
    @black = black
  end

  def to_s
    board.each.with_object("") { |row, s|
      s << row.join(" ") << "\n"
    }.chomp
  end

  def attack?
    same_row? || same_column? || same_diagonol?
  end

  private

  def board
    board = Array.new(BOARD_SIZE) {
      Array.new(BOARD_SIZE) { "O" }
    }
    board[white_row][white_column] = "W"
    board[black_row][black_column] = "B"
    board
  end

  def white_row
    white[0]
  end

  def white_column
    white[1]
  end

  def black_row
    black[0]
  end

  def black_column
    black[1]
  end

  def same_row?
    white_row == black_row
  end

  def same_column?
    white_column == black_column
  end

  def same_diagonol?
    white_row - black_column == white_column - black_row ||
      white_row + black_column == white_column + black_row
  end

end
