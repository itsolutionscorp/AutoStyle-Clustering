class Queens
  attr_reader :white, :black
  def initialize (dict={})
    @white = dict[:white] || [0, 3]
    @black = dict[:black] || [7, 3]
    raise ArgumentError if @white == @black
  end
  def to_s
    (0..7).map{|i|
      (0..7).map{|j|
        if @white == [i, j] then "W"
        elsif @black == [i, j] then "B"
        else "O" end
      }.join(" ")
    }.join("\n")
  end
  def attack?
    @white[0] == @black[0] or @white[1] == @black[1] or
      (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end
end
