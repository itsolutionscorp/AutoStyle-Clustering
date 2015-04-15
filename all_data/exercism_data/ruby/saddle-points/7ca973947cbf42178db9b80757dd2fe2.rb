class Matrix
  def initialize(matrix_str)
    @matrix_str = matrix_str
  end

  def rows
    matrix_str.each_line.map { |line| line.split.map(&:to_i) }
  end

  def columns
    rows.transpose
  end

  def saddle_points
    rows.each_with_index.with_object([]) do |(row, row_index), points|
      row.each_with_index do |value, col_index|
        points << [row_index, col_index] if saddle_point?(value, row, col_index)
      end
    end
  end

  private

  attr_reader :matrix_str

  def saddle_point?(value, row, col_index)
    value == row.max && value == columns[col_index].min
  end
end
