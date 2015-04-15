class Matrix
  attr_reader :rows

  def initialize(matrix)
    @rows = matrix.lines.map { |row| row.split(" ").map(&:to_i) }
  end

  def columns
    rows.transpose
  end

  def saddle_points
    [].tap do |result|
      rows.each_with_index do |row, i|
        j = row.find_index(row.max)
        result << [i, j] if columns[j].min == row.max
      end
    end
  end
end
