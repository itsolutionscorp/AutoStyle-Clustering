class Queens
  attr_reader :board
  def initialize(positions=nil)
    if positions
      raise ArgumentError if positions[:white] == positions[:black]
      @white_x, @white_y = positions[:white]
      @black_x, @black_y = positions[:black]
    else
      @white_x, @white_y = [0,3]
      @black_x, @black_y = [7,3]
    end
    @board = Board.new([@white_x, @white_y], [@black_x, @black_y])
  end

  def white
    [@white_x, @white_y]
  end

  def black
    [@black_x, @black_y]
  end

  def to_s
    board.to_s
  end

  def attack?
    on_same_row? || on_same_column? || on_diagonal?
  end

  private
  def white_row; white.first; end
  def white_column; white.last; end
  def black_row; black.first; end
  def black_column; black.last; end

  def on_same_row?
    white_row == black_row
  end

  def on_same_column?
    white_column == black_column
  end

  def on_diagonal?
    row_delta == column_delta
  end

  def row_delta
    (white_row - black_row).abs
  end

  def column_delta
    (white_column - black_column).abs
  end
end

class Board
  attr_reader :size, :blank_sym, :white_coords, :black_coords
  def initialize(white_coords, black_coords)
    @size = 8
    @blank_sym = '_'
    @white_coords = white_coords
    @black_coords = black_coords
  end

  def to_s
    rows.map { |row| row.join(' ') }.join("\n")
  end

  private

  def rows
    0.upto(size-1).map do |ri|
      empty_row.each_with_index.map do |sym, ci|
        if white_coords.first == ri && white_coords.last == ci
          'W'
        elsif black_coords.first == ri && black_coords.last == ci
          'B'
        else
          sym
        end
      end
    end
  end

  def empty_row
    [blank_sym]*size
  end
end
