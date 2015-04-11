require 'set'

class Queens
  ROW_SIZE= 8
  COL_SIZE= 8

  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError unless valid?(white, black)

    @white = white
    @black = black
  end

  def attack?
    case
    when same_line?, diagonal?
      true
    else
      false
    end
  end

  def to_s
    rows = []
    (0 .. ROW_SIZE - 1).each do |y|
      col = []
      (0 .. COL_SIZE - 1).each do |x|
        case
        when @white == [y, x]
          col << "W"
        when @black == [y, x]
          col << "B"
        else
          col << "_"
        end
      end
      rows << col.join(" ")
    end

    rows.join("\n")
  end

  private
  def valid?(white, black)
    white != black
  end

  def same_line?
    @white.first == @black.first or @white.last == @black.last
  end

  def diagonal?
    (@white.first - @black.first).abs == (@white.last - @black.last).abs
  end
end
