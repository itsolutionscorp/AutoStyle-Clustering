class Triangle
  def initialize(row_count)
    @row_count = row_count
  end

  def rows
    1.upto(@row_count).map { |n| row(n) }
  end

  private

  def row(number)
    1.upto(number).map { |col| value(number, col) }
  end

  def value(row_number, column_number)
    return 0 if column_number < 1
    return 0 if column_number > row_number

    if row_number == 1
      1
    else
      left  = value(row_number.pred, column_number.pred)
      right = value(row_number.pred, column_number)
      left + right
    end
  end
end
