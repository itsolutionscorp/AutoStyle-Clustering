class Board
  attr_reader :board

  def self.transform(board)
    new(board).transform.to_s
  end

  ADJACENT_POSITIONS = [ 
    [0,-1], [0, 1], [1, 0], [ -1, 0],  # down, up, right, left
    [1, 1], [1,-1], [-1,-1], [-1, 1]   # diagnals
  ]

  EMPTY = ' '
  MINE  = '*'

  def initialize(board)
    @board = board
    validate_board
  end

  def transform
    each_with_coordinates do |r,c|
      if board[r][c] == EMPTY
        count = count_mines(r,c).to_s
        board[r][c] = count unless count == '0'
      end 
    end
    self
  end

  def to_s
    board
  end

  private

  def each_with_coordinates
    board.each_with_index do |row, r|
      row.chars.each_index do |c|
        yield(r,c)
      end
    end
  end

  def count_mines(r,c)
    ADJACENT_POSITIONS.reduce(0) do |count, (x,y)|
      board[r+x][c+y] == MINE ? count +=1 : count
    end
  end

  def validate_board
    if !valid_rows? || !same_column_sizes?
       raise ValueError
    end
  end

  def valid_rows?
    valid_mid_rows? && valid_top_bottom_rows?
  end

  def valid_mid_rows?
    board[1..-2].all? { |row| row[/\|[\s\*]+\|/] }
  end

  def valid_top_bottom_rows?
    top, bottom = board.first, board.last
    [top, bottom].all? { |row| row[/\+-+\+/] }
  end

  def same_column_sizes?
    size = board.first.size
    board.all? { |col| col.size == size }
  end
end

class ValueError < ArgumentError; end
