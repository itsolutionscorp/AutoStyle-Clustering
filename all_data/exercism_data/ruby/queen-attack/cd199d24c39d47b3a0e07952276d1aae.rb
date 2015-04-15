class Queens
  attr_reader :white, :black
  def initialize args = {white: [0, 3], black: [7, 3]}
    @white, @black = args[:white], args[:black]
    raise ArgumentError if white == black
  end

  def to_s
    board = 8.times.collect { Array.new(8, '_') }
    board[white[0]][white[1]] = 'W'
    board[black[0]][black[1]] = 'B'
    board.collect { |row| row.join(' ') }.join("\n")
  end
  
  def attack?
    row? || col? || diag?
  end
  
  private
  def row?
    white[0] == black[0]
  end

  def col?
    white[1] == black[1]
  end
  
  def diag?
    (white[0]-black[0]).abs == (white[1]-black[1]).abs
  end
end
