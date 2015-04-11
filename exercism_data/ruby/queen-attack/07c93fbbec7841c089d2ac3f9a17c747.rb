class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    fail ArgumentError, "Pieces cannot occupy the same space" if white == black
    @white = white
    @black = black
  end

  def to_s
    board.map { |row| row.join(" ") }.join("\n")
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  private

  def board
    empty_board.tap do |board|
      board[white[0]][white[1]] = "W"
      board[black[0]][black[1]] = "B"
    end
  end

  def empty_board
    Array.new(8) { Array.new(8, "_") }
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def same_diagonal?
    y_diff = (white[0] - black[0]).abs
    x_diff = (white[1] - black[1]).abs
    y_diff == x_diff
  end
end
