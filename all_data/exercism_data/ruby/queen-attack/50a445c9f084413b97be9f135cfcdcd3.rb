class Queens

  SQUARES_ON_A_BOARD = 8

  attr_reader :white, :black, :board

  def initialize(options={})
    white = options[:white] || [0,3]
    black = options[:black] || [7,3]
    @white = Queen.new(white).position
    @black = Queen.new(black).position
    @board = draw_board
    place_pieces
    invalid_positions?
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def same_diagonal?
    (white[1] - black[1]).abs == (white[0] - black[0]).abs
  end

  def to_s
    board.map.with_index do |row, column|
      row.join(" ")
    end.join("\n")
  end

  private
  def invalid_positions?
    raise ArgumentError if white == black
  end

  def draw_board
    Array.new(8){Array.new(8){ "_" } }
  end

  def place_pieces
    board[white[0]][white[1]] = "W"
    board[black[0]][black[1]] = "B"
  end

end

class Queen

  attr_reader :position

  def initialize(coordinates)
    @position = coordinates
  end

end
