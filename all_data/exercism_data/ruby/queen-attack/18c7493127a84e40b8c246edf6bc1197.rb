class Queens
  attr_reader :black, :white
  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError if white == black
    @white = white
    @black = black
  end

  def to_s
    board = Array.new(8){ ["_"] * 8 }
    board[white[0]][white[1]] = "W"
    board[black[0]][black[1]] = "B"
    board.map { |row| row.join(" ") }.join("\n")
  end

  def attack?
    same_row_or_column? || diagonal?
  end

  def same_row_or_column?
    (0..1).any? { |index| white[index] == black[index] }
  end

  def diagonal?
    white.reduce(:-).abs == black.reduce(:-).abs
  end
end
