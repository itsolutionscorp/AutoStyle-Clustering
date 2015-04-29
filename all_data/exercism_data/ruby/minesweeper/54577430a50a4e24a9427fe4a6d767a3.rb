class ValueError < StandardError;  end;
class Tile

  attr_reader :row, :col, :mine

  def initialize(row, col, mine)
    @row = row
    @col = col
    @mine = mine ? 1 : 0
  end

  def mine?
    mine == 1
  end

  def count_mines(board)
    count = 0
    for i in -1..1 do
      for j in -1..1 do
        count = count + board.mine(row + i,col + j) unless i==0 && j==0
      end  
    end
    count  
  end

  def hint(board)
    return "*" if mine?
    c = count_mines(board)
    return " " if c == 0
    c.to_s
  end

end

class Board

  attr_reader  :rows, :cols, :grid

  def initialize(input)
    validate(input)
    @hz_border = input.first
    @vr_border = input[1][0]
    @rows = input.length - 2
    @cols = input.first.length - 2
    @grid = strip_border(input)
  end

  def mine(row, col)
    return 0 if row >= @rows || col >= @cols || row < 0 || col < 0
    @grid[row][col].mine
  end

  def hints_with_border
    h = hints.map! { |row| row.insert(0,@vr_border).insert(@cols+1,@vr_border) }
    h.insert(0,@hz_border).insert(@rows+1,@hz_border)
  end

  def self.transform(input)
    Board.new(input).hints_with_border
  end

  private
  HZ_BORDER_REGEX = /\+-+\+/

  def strip_border(input)
    @grid = Array.new(rows) { |i| Array.new(cols) { |i| nil } }
    input.delete(input.first)
    input.each_with_index do |row, row_idx|
      row.delete("|").split("").each_with_index do |tile, col_idx|
        @grid[row_idx][col_idx]=Tile.new(row_idx,col_idx,mine?(tile))
      end
    end
    @grid
  end

  def hints
    h = Array.new(@rows){" "*(@cols) }
    @grid.each do |row|
      row.each do |tile|
        h[tile.row][tile.col] = tile.hint(self)
      end
    end
    h
  end

  def mine?(char)
    char == "*"
  end

  def validate(input)
    raise ValueError unless HZ_BORDER_REGEX.match(input.first) && HZ_BORDER_REGEX.match(input.last)
    input.each do |row|
      raise ValueError if row.length != input.first.length
      unless row == input.first || row == input.last then
        raise ValueError unless /^(\|){1}(\s|\*)+(\|){1}$/.match(row)
      end
    end
  end

end
