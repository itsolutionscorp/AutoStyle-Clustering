class Queens
  attr_reader :white, :black

  def initialize coords = { white: [0, 3], black: [7, 3] }
    @white, @black = coords[:white], coords[:black]
    fail ArgumentError if white == black
    setup_board
    place_queens
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  def to_s
    @board.map { |row| row.join ' ' }.join "\n"
  end

  private

  def setup_board
    @board = Array.new(64, 'O').each_slice(8).to_a
  end

  def place_queens
    [white.dup << 'W', black.dup << 'B'].each { |x, y, m| @board[x][y] = m }
  end

  def same_row?
    x_coords.uniq.size == 1
  end

  def same_column?
    y_coords.uniq.size == 1
  end

  def same_diagonal?
    difference(x_coords) == difference(y_coords)
  end

  def x_coords
    [white, black].map { |x, _y| x }
  end

  def y_coords
    [white, black].map { |_x, y| y }
  end

  def difference arr
    (arr[0] - arr[1]).abs
  end
end
