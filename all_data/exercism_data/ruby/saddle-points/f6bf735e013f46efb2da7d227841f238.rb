class Matrix
  def initialize(matrix)
    @matrix = matrix.split(/\s+/).map(&:to_i)
    @width = matrix.gsub(/\S/, "").index("\n") + 1
  end

  def rows
    @matrix.each_slice(@width).to_a
  end

  def columns
    rows.transpose
  end

  def saddle_points
    @matrix
      .each_index.map(&method(:get_row_col))
      .select.with_index { |rc, i| @matrix[i] == rows[rc.first].max && @matrix[i] == columns[rc.last].min }
  end

  def get_row_col(i)
    [i / @width, i % @width]
  end
end
