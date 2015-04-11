class Matrix
  attr_reader :rows, :columns

  def initialize(string)
    @rows = string.lines.map { |row| row.split.map(&:to_i) }
    @columns = @rows.transpose
  end

  def saddle_points
    points = Array(0...rows.size).product(Array(0...columns.size))
    points.select { |r, c| saddle_point?(r, c) }
  end

  def saddle_point?(r, c)
    value = rows[r][c]
    value == rows[r].max && value == columns[c].min
  end

end
