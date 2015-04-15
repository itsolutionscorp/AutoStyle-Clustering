class Triangle
  def initialize(max_row)
    @max = max_row
  end

  def rows
    @triangle ||= (1..@max - 1).each_with_object([[1]]) do |row_num, triangle|
      triangle << (0..row_num).each_with_object([]) do |col_num, row|
        row << (left(triangle, row_num, col_num) + right(triangle, row_num, col_num))
      end
    end
  end

  private
  def left(triangle, row, col)
    return 0 if col == 0
    triangle[row - 1][col - 1]
  end

  def right(triangle, row, col)
    return 0 if col == row
    triangle[row - 1][col]
  end

end
