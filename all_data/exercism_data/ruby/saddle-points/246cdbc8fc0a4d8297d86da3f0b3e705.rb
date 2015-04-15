class Matrix

  attr_reader :rows, :columns

  def initialize(string)
    @rows    = parse(string)
    @columns = @rows.transpose
  end

  def saddle_points
    coordinates.select do |row, col|
      max_in_row?(row,col) && min_in_col?(row,col)
    end
  end

  private
  def coordinates
    (0...rows.size).to_a.product (0...columns.size).to_a
  end

  def max_in_row?(row,col)
    rows[row][col] >= rows[row].max
  end

  def min_in_col?(row,col)
    rows[row][col] <= columns[col].min
  end

  def parse(string)
    string.lines.map { |row| row.split.map(&:to_i) }
  end

end
