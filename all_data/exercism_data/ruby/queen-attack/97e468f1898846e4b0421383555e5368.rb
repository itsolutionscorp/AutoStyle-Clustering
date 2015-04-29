class Queens
  attr_reader :white, :black

  def initialize white: [0, 3], black: [7, 3]
    raise ArgumentError if white == black
    @white, @black = white, black
  end

  def to_s
    (0..7).map do |x|
      (0..7).map do |y| 
        [x, y] == white ? 'W' : ([x, y] == black ? 'B' : '_')
      end.join(' ')
    end.join("\n")
  end

  def attack?
    dx = white[0] - black[0]
    dy = white[1] - black[1]
    return dx == 0 || dy == 0 || dx.abs == dy.abs
  end
end
