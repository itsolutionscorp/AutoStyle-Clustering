class Queens
  attr_reader :white, :black, :board

  def initialize(options={})
    @white = options[:white] || [0, 3]
    @black = options[:black] || [7, 3]
    raise ArgumentError if white == black
    @board = Array.new(8) { Array.new(8, '_') }
    board[white[0]][white[1]] = 'W'
    board[black[0]][black[1]] = 'B'
  end

  def to_s
    board.map { |row| row.join(' ') }.join("\n")
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def same_row?
    white[0] == black[0]
  end

  def same_column?
    white[1] == black[1]
  end

  def same_diagonal?
    (white[0] - black[0]).abs == (white[1] - black[1]).abs
  end

end
