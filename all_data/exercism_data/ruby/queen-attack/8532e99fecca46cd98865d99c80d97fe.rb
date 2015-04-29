class Queens
  attr_reader :white, :black

  def initialize(positions={})
    @white = positions.fetch(:white, [0, 3])
    @black = positions.fetch(:black, [7, 3])
    raise ArgumentError if @white == @black
  end

  def to_s
    board = Array.new(8){Array.new(8, 'O')}
    board[@white[0]][@white[1]] = 'W'
    board[@black[0]][@black[1]] = 'B'
    board.map{|row| row.join(" ")}.join("\n")
  end

  def attack?
    @black[0] == @white[0] || @black[1] == @white[1] ||
      (@black[0]-@white[0]).abs == (@black[1]-@white[1]).abs
  end
end
