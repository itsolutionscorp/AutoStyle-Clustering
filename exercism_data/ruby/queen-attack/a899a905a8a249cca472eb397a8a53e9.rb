class Queens
  attr_reader :white, :black

  def initialize(white: [0, 3], black: [7, 3])
    raise ArgumentError if white == black

    @white = white
    @black = black
    @white_queen = Queen.new(white, 'W')
    @black_queen = Queen.new(black, 'B')
  end

  def to_s
    board = Array.new(8) { Array.new(8, '_') }

    place_queens_on_board(board)

    board.map { |row| row.join(' ') }.join("\n")
  end

  def attack?
    same_row? || same_column? || same_diagonal?
  end

  private

  attr_reader :white_queen, :black_queen

  def place_queens_on_board(board)
    place_queen_on_board(white_queen, board)
    place_queen_on_board(black_queen, board)
  end

  def place_queen_on_board(queen, board)
    board[queen.row][queen.column] = queen.marker
  end

  def same_row?
    white_queen.same_row_as?(black_queen)
  end

  def same_column?
    white_queen.same_column_as?(black_queen)
  end

  def same_diagonal?
    white_queen.same_diagonal_as?(black_queen)
  end

  class Queen
    attr_reader :row, :column, :marker

    def initialize(location, marker)
      @row, @column = location
      @marker       = marker
    end

    def same_row_as?(other_piece)
      row == other_piece.row
    end

    def same_column_as?(other_piece)
      column == other_piece.column
    end

    def same_diagonal_as?(other_piece)
      (row - other_piece.row).abs == (column - other_piece.column).abs
    end
  end
end
