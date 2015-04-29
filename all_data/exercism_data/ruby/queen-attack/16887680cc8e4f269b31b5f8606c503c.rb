class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError if white == black
    @white, @black = [white,black].map { |o| o.extend Positioned }
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def to_s
    rows.join "\n"
  end

private
  module Positioned
    def row
      self[0]
    end

    def column
      self[1]
    end
  end

  def same_row?
    white.row == black.row
  end

  def same_column?
    white.column == black.column
  end

  def same_diagonal?
    (white.row - black.row).abs == (white.column - black.column).abs
  end

  def rows
    (0..7).map { |row| cells_in(row).join " " }
  end

  def cells_in(row)
    (0..7).map { |column| cell row, column }
  end

  def cell(*position)
    case position
    when white then "W"
    when black then "B"
    else "O"
    end
  end
end
