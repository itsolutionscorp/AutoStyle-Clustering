class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    @white = white
    @black = black

    raise ArgumentError if @white == @black
  end

  def to_s
    8.times.map{|x| 8.times.map{|y| symbol_for_pos(x,y)}.join(" ")}.join("\n")
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  private

  def symbol_for_pos(x,y)
    case
    when [x,y] == @white then "W"
    when [x,y] == @black then "B"
    else "O"
    end
  end

  def same_row?
    @white[0] == @black[0]
  end

  def same_column?
    @white[1] == @black[1]
  end

  def same_diagonal?
    diagonal_1 = @white[0] - @white[1] == @black[0] - @black[1]
    diagonal_2 = @white[0] + @white[1] == @black[0] + @black[1]
    diagonal_1 || diagonal_2
  end
end
