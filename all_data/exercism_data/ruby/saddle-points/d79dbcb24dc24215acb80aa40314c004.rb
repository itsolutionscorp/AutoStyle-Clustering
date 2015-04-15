class Matrix
  attr_reader :matrix, :row, :column

  def initialize(str)
    @matrix = str.split("\n").map { |r| r.split(' ').map(&:to_i) }
    @row = rows.length
    @column = columns.length
    @saddle_points = nil
  end

  def rows
    matrix
  end

  def columns
    matrix.transpose
  end

  def saddle_points
    generate_saddle_points if @saddle_points.nil?
    @saddle_points
  end

  def saddle_point?(r, c)
    number = rows[r][c]
    saddle_point = true
    rows[r].each { |n| saddle_point = false unless number >= n }
    columns[c].each { |n| saddle_point = false unless number <= n }
    saddle_point
  end

  private

  def generate_saddle_points
    @saddle_points = []
    row.times do |r|
      column.times do |c|
        @saddle_points << [r, c] if saddle_point?(r, c)
      end
    end
  end
end
