class Matrix
  def initialize(string)
    @matrix = string.lines.map { |row| row.split.map(&:to_i) }
  end

  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end

  def saddle_points
    coords.select do |i, j|
      matrix[i][j] == rows[i].max && matrix[i][j] == columns[j].min
    end
  end

  private
  attr_reader :matrix

  def coords
    rows.size.times.to_a.product(columns.size.times.to_a)
  end
end
