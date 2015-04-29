class Matrix
  attr_reader :rows

  def initialize(matrix)
    @rows = matrix.lines.map { |line| line.split.map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end

  def saddle_points
    rows.map.with_index.with_object([]) do |(row, y), saddle_points|
      row.each_with_index do |value, x|
        column = columns[x]

        if row.all? { |n| value >= n } && column.all? { |n| value <= n }
          saddle_points << [y, x]
        end
      end
    end
  end
end
