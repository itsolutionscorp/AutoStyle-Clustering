class Queens
  attr_reader :white, :black

  def initialize white: [0, 3], black: [7, 3]
    fail ArgumentError if white == black
    @white, @black = white, black
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
    [white.dup << 'W', black.dup << 'B'].each do |row, col, m|
      @board[row][col] = m
    end
  end

  def same_row?
    row_coords.uniq.size == 1
  end

  def same_column?
    col_coords.uniq.size == 1
  end

  def same_diagonal?
    difference(row_coords) == difference(col_coords)
  end

  def row_coords
    [white, black].map { |row, _col| row }
  end

  def col_coords
    [white, black].map { |_row, col| col }
  end

  def difference arr
    (arr[0] - arr[1]).abs
  end
end
