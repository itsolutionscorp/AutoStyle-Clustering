class ChessBoard

  SIZE             = 8
  COLUMN_SEPARATOR = " "
  ROW_SEPARATOR    = "\n"

  def render
    (0...SIZE).map do |row|
      (0...SIZE).map do |col|
        yield row, col
      end.join(COLUMN_SEPARATOR)
    end.join(ROW_SEPARATOR)
  end

end

class Queens

  DEFAULT_POSITIONS = {
    white: [0, 3],
    black: [7, 3]
  }

  attr_reader :white, :black

  def initialize(positions = {})
    start_positions = DEFAULT_POSITIONS.merge(positions)
    @white = start_positions[:white]
    @black = start_positions[:black]
    raise ArgumentError, "cannot occupy same space" if same_space?
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  def to_s
    ChessBoard.new.render do |row, col|
      render_board_item(row, col)
    end
  end

  private

  def same_space?
    same_row? && same_column?
  end

  def same_row?
    @white[0] == @black[0]
  end

  def same_column?
    @white[1] == @black[1]
  end

  def diagonal?
    dy = (@white[0] - @black[0]).abs
    dx = (@white[1] - @black[1]).abs
    dx == dy
  end

  BOARD_ITEM_WHITE = "W"
  BOARD_ITEM_BLACK = "B"
  BOARD_ITEM_EMPTY = "O"

  def render_board_item(row, col)
    case [row, col]
      when @white then BOARD_ITEM_WHITE
      when @black then BOARD_ITEM_BLACK
                  else BOARD_ITEM_EMPTY
    end
  end

end
