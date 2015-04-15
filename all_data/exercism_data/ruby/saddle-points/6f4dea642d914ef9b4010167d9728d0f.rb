class Matrix
  def initialize(input)
    @raw_data = input
  end

  def rows
    @rows ||= @raw_data.lines.map { |r| r.split(" ").map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end

  def saddle_points
    @saddle_points ||= set_saddle_points
  end

  private

  def set_saddle_points
    result = []
    rows.each_with_index do |row, row_index|
      candidate = row.max
      col_index = row.index(candidate)

      result << [row_index, col_index] if columns[col_index].min >= candidate
    end
    result
  end

end
