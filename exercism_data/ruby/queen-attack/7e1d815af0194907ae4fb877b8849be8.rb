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
    string = ""
    @board.each do |row|
      row.each { |col| string << "#{col} " }
      string.chop!
      string << "\n"
    end
    string.chomp!
    string
  end

  def attack?
    @black.first == @white.first || @black.last == @white.last || diagonals?
  end

  def diagonals?
    up_right?
  end

  def up_right?
    start = [@black, @white].min
    ctr = 0
    8.times do
      return true if @board[start.first + ctr][start.last + ctr] != '_'
      ctr += 1
    end
    false
  end

end
