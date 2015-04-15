class Matrix
  def initialize string
    @matrix = string.split("\n").map { |row| row.split(' ').map(&:to_i) }
  end

  def rows
    return Array.new @matrix
  end

  def columns
    return @matrix.transpose
  end

  def saddle_points
    column_mins = columns.map(&:min)
    rows.zip(rows.map(&:max)).each_with_index.flat_map do |(row, row_max), x|
      row.zip(column_mins).each_with_index.map do |(value, column_min), y|
        [x, y] if value == row_max && value == column_min
      end
    end.compact
  end
end
