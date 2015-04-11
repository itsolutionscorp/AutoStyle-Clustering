class Queens

  attr_reader :white,
              :black

  def initialize(white: [0,3], black: [7,3])
    raise ArgumentError if white == black
    @white = white
    @black = black
  end

  def board
    @board ||= Board.new
  end

  def position_on_board
    board.position_white(*white)
    board.position_black(*black)
  end

  def to_s
    position_on_board
    board.to_s
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def diagonal?
     forward_diagonal? || backward_diagonal?
  end

  def forward_diagonal?
    white.reduce(&:+) == black.reduce(&:+)
  end

  def backward_diagonal?
    white.reduce(&:-) == black.reduce(&:-)
  end
end

class Board

  attr_reader :layout

  def initialize
    @layout = (1..8).to_a.map { (1..8).to_a.map { "O" } }
  end

  def position_white(row, column)
    layout[row][column] = "W"
  end

  def position_black(row, column)
    layout[row][column] = "B"
  end

  def to_s
    layout.map { |row| row.join(' ') }.join("\n")
  end

end
