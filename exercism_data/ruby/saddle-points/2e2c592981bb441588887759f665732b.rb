class Matrix
  attr_reader :rows

  def initialize(matrix)
    @rows = matrix.lines.map { |row| row.split.map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end

  def saddle_points
    [].tap do |saddles|
      rows.each_with_index do |row, i|
        max = row.max
        j = row.find_index(max)
        saddles << [i, j] if columns[j].min == max
      end
    end
  end

end
