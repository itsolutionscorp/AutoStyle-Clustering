class Queens
  
  DEFAULT_WHITE = [0, 3]
  DEFAULT_BLACK = [7, 3]
  
  def initialize(white: DEFAULT_WHITE, black: DEFAULT_BLACK)
    @white = white.extend(Positioned)
    @black = black.extend(Positioned)

    fail ArgumentError, "Queens can't occupy the same position" \
      if @white == @black
  end

  attr_reader :white, :black

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def to_s
    (0...8).map { |r| print_row(r) }.join "\n"
  end

  private

  def same_row?
    @white.row == @black.row
  end

  def same_column?
    @white.col == @black.col
  end

  def same_diagonal?
    (@white.row - @black.row).abs == (@white.col - @black.col).abs
  end

  module Positioned
    def row; self[0] end
    def col; self[1] end
  end
    
  def print_row(row)
    (0...8).map do |col|
      case
      when @white == [row, col] then 'W'
      when @black == [row, col] then 'B'
      else '_'
      end
    end.join ' '
  end
end
