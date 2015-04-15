class Queens
  attr_reader :white, :black

  def initialize(white: [0,3], black: [7,3])
    fail ArgumentError if white == black
    @white = white
    @black = black
  end

  def attack?
    x_diff = (white[0] - black[0]).abs
    y_diff = (white[1] - black[1]).abs

    x_diff.zero? || y_diff.zero? || x_diff == y_diff      
  end

  def to_s
    board = Array.new(64,'_')
    board[white[0]*8+white[1]] = 'W'
    board[black[0]*8+black[1]] = 'B'

    board.each_slice(8).map{ |c| c.join(' ') }.join("\n")
  end
end
