class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError, "Cannot occupy the same space" if white == black
    @white = white
    @black = black
  end

  def to_s
    8.times.each_with_object([]) { |row, rows|
      rows << 8.times.each_with_object([]) { |col, cols|
        cols << symbol_for(row, col)
    }.join(" ") }.join("\n")
  end

  def attack?
    same_row? || same_col? || diagonal?
  end

  private
  def symbol_for(row, col)
    return "W" if @white == [row, col]
    return "B" if @black == [row, col]
    "O"
  end

  def same_row?
    @black[0] == @white[0]
  end

  def same_col?
    @black[1] == @white[1]
  end

  def diagonal?
    (@black[0] - @white[0]).abs == (@black[1] - @white[1]).abs
  end
end
