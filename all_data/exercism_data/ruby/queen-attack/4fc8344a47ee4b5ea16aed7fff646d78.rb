class Queens
  attr_reader :white, :black
  def initialize(white: [0,3], black: [7,3])
    @board = build_board(white, black)
    @black = current_position('B')
    @white = current_position('W')
  end

  def build_board(white, black)
    board = Array.new(8, "_") { Array.new(8, "_") }
    if white == black
      raise ArgumentError
    else
      board[white.first][white.last] = 'W'
      board[black.first][black.last] = 'B'
    end
    board
  end

  def current_position(color)
    results = []
    @board.each_with_index do |row, i|
      results.push(i, row.index(color)) if row.include?(color)
    end
    results
  end

  def to_s
    @board.collect { |row| row.join(' ') }.join("\n")
  end

  def attack?
   row_attack? || col_attack? || diagonals?
  end

  def row_attack?
    @black.first == @white.first
  end

  def col_attack?
    @black.last == @white.last
  end

  def diagonals?
    (@white[1] - @white[0]).abs == (@black[1] - @black[0]).abs
  end

end
