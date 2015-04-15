class Queens
  attr_reader :white, :black, :board

  def initialize coords = { white: [0, 3], black: [7, 3] }
    fail ArgumentError if coords[:white] == coords[:black]
    @white = coords[:white]
    @black = coords[:black]
    @board = Array.new(64).fill('O').each_slice(8).to_a
    place_queens
  end

  def attack?
    same_row? || same_column? || on_diagonal?
  end

  def to_s
    board.map { |row| row.join ' ' }.join "\n"
  end

  private

  def place_queens
    @board[white[0]][white[1]] = 'W'
    @board[black[0]][black[1]] = 'B'
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def on_diagonal?
    (white[0] - black[0]).abs == (white[1] - black[1]).abs
  end
end
