class Triangle
  def initialize(num_rows)
    @num_rows = num_rows
  end

  def rows
    num_rows.times.each_with_object([]) do |row_num, triangle|
      triangle << row(row_num, triangle.last)
    end
  end

  private

  attr_reader :num_rows

  def row(row_num, last_row)
    Array.new(row_num + 1) do |index|
      next 1 if index.zero? || index == row_num
      last_row[index - 1] + last_row[index]
    end
  end
end
