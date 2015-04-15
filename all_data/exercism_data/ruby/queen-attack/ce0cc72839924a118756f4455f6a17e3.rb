class Queens
  attr_reader :white, :black

  def initialize( d={ :white => [0, 3], :black => [7, 3] })
    raise ArgumentError if d[:white] == d[:black]
    @white = d[:white]
    @black = d[:black]
  end

  def to_s
    row = ["_"] * 8
    board = []
    8.times { board << row.clone }
    board[@white[0]][@white[1]] = "W"
    board[@black[0]][@black[1]] = "B"
    board.map { |r| r.join(" ") } .join("\n")
  end

  def attack?
    @white[0] == @black[0] or
      @white[1] == @black[1] or
      (@white[0] - @black[0]).abs == (@white[1] - @black[1]).abs
  end
end
