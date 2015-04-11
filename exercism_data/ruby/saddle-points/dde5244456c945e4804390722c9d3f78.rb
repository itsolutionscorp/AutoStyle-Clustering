class Matrix
  attr_reader :rows
  def initialize(input)
    @rows = parse_input(input)
  end

  def columns
    rows.transpose
  end

  def saddle_points
    all_coords.select do |row, col|
       max_in_row?(row,col) && min_in_col?(row, col)
    end
  end

  def max_in_row?(row,col)
    rows[row][col]>= rows[row].max
  end

  def min_in_col?(row,col)
    rows[row][col] <= columns[col].min
  end

  def all_coords
    (0...rows.size).to_a.product (0...columns.size).to_a
  end

  private
  def parse_input(input)
    input.split("\n").map do |line|
      line.split(" ").map(&:to_i)
    end
  end
end
