class Triangle
  def initialize(num_rows)
    @num_rows = num_rows
  end

  def rows
    num_rows.times.each_with_object([]) do |row, triangle|
      triangle << Array.new(row + 1) do |index|
        next 1 if index.zero? || index == row

        previous_row = triangle[row - 1]
        previous_row[index - 1] + previous_row[index]
      end
    end
  end

  private

  attr_reader :num_rows
end
