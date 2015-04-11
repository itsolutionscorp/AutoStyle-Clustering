class Queens
  DEFAULT_WHITE_POSITION = [0, 3]
  DEFAULT_BLACK_POSITION = [7, 3]
  BOARD_SIDE = 8

  attr_reader :white, :black

  def initialize(white: DEFAULT_WHITE_POSITION, black: DEFAULT_BLACK_POSITION)
    @white = white
    @black = black
    assert_different_position
  end

  def to_s
    render_board
  end

  def attack?
    wpos = Position.new(*white)
    bpos = Position.new(*black)

    same_row = wpos.x == bpos.x
    same_col = wpos.y == bpos.y
    same_diagonal = wpos.diagonal == bpos.diagonal

    same_row || same_col || same_diagonal
  end

  private

  def render_board
    BOARD_SIDE.times.map { |x|
      BOARD_SIDE.times.map { |y|
        symbol_for(x, y)
      }.join(" ")
    }.join("\n")
  end

  def symbol_for(x, y)
    case [x, y]
    when white then "W"
    when black then "B"
    else            "O"
    end
  end

  def assert_different_position
    raise ArgumentError, "Can't occupy same position" if white == black
  end

  Position = Struct.new(:x, :y) do
    def diagonal
      x - y
    end
  end
end
