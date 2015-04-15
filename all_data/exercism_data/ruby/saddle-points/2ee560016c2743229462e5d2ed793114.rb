class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = parse(string)
  end

  def columns
    rows.transpose
  end

  def saddle_points
    row_maxes = rows.map(&:max)
    col_mins  = columns.map(&:min)

    out = []
    rows.each_with_index do |row, row_i|
      row.each_with_index do |value, col_i|
        row_max = row_maxes[row_i]
        col_min = col_mins[col_i]
        out << [row_i, col_i] if value >= row_max && value <= col_min
      end
    end
    out
  end

  private

  def parse(string)
    string.lines.map { |row| row.split(" ").map(&:to_i) }
  end
end
