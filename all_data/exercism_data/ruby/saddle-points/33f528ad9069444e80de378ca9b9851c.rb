class Matrix
  attr_reader :rows, :columns

  def initialize str
    @rows = determine_rows str
    @columns = rows.transpose
  end

  def saddle_points
    @rows.each.with_index.with_object([]) do |(row, x), sp|
      row.each.with_index do |e, y|
        sp << [x, y] if compare_to_row(e, row) &&
                        compare_to_column(e, columns[y])
      end
    end
  end

  private

  def determine_rows str
    str.split("\n").map { |l| l.split(' ').map { |n| n.to_i } }
  end

  def compare_to_row e, row
    row.all? { |n| e >= n }
  end

  def compare_to_column e, column
    column.all? { |n| e <= n }
  end
end
