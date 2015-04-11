class Queens
  attr_reader :white, :black

  def initialize(positions=starting_positions)
    raise ArgumentError if positions[:white] == positions[:black]

    @white = positions[:white]
    @black = positions[:black]
  end

  def to_s
    board = Board.new
    board.place_piece(white, "W")
    board.place_piece(black, "B")
    board.to_s
  end

  def attack?
    [same_row?, same_column?, same_diagonal?].any?
  end

  private

  def starting_positions
    {:white => [0, 3], :black => [7, 3]}
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def same_diagonal?
    offset(white) == offset(black)
  end

  def offset(color)
    (color[1] - color[0]).abs
  end

end

class Board
  attr_accessor :squares

  def initialize
    @squares = Array.new(8) {Array.new(8, "O")}
  end

  def place_piece(coordinates, color)
    x, y = coordinates[0], coordinates[1]
    @squares[x][y] = color
  end

  def to_s
    squares.map do |row|
      row.join(" ")
    end.join("\n")
  end
end
