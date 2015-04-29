class Queens
  attr_accessor :white, :black

  def initialize positions={}
    @white = positions[:white] || [0, 3]
    @black = positions[:black] || [7, 3]
    permited_positions?
  end

  def to_s
    Board.new.set_queens_positions(white, black).to_s
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  private

  def permited_positions?
    raise ArgumentError if white == black
  end

  def same_row?
    white.first == black.first
  end

  def same_column?
    white.last == black.last
  end

  def diagonal?
    (white.first - black.first).abs ==
      (black.last - white.last).abs
  end
end

class Board
  def initialize
    @board = Array.new(8){ Array.new(8){ "_" } }
  end

  def set_queens_positions white, black
    @board[white.first][white.last] = "W"
    @board[black.first][black.last] = "B"
    self
  end

  def to_s
    @board.map { |row| row.join(" ") }.join("\n")
  end
end
