class Matrix
  attr_reader :rows, :columns

  def initialize(string)
    @rows = string.each_line.map { |row| row.split.map(&:to_i) }
    @columns = rows.transpose
  end

  def saddle_points
    each_coordinate.select do |i, j|
      rows[i].all? { |e| rows[i][j] >= e } &&
        columns[j].all? { |e| rows[i][j] <= e }
    end
  end

  private

  def each_coordinate
    return to_enum(:each_coordinate) unless block_given?

    rows.each_index do |i|
      columns.each_index { |j| yield i, j }
    end
  end
end
