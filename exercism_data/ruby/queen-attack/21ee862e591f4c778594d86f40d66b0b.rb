class Queens
  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError if white == black
    @white, @black = white, black
  end

  def white
    @white
  end

  def black
    @black
  end

  def attack?
    same_row? || same_column? || diagonal?
  end

  def to_s
    (0..7).map do |row|
      (0..7).map do |column|
        cell row, column
      end.join " " 
    end.join "\n"
  end

private
  def same_row?
    @white[0] == @black[0]
  end

  def same_column?
    @white[1] == @black[1]
  end

  def diagonal?
    (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end

  def cell(*position)
    white_queen_cell(position) || black_queen_cell(position) || "O"
  end

  def white_queen_cell(position)
    "W" if @white == position
  end

  def black_queen_cell(position)
    "B" if @black == position
  end
end
