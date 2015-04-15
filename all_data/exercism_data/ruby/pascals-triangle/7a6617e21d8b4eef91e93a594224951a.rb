class Triangle
  def initialize(row_count)
    @row_count = row_count
  end

  def rows
    1.upto(@row_count).map { |n| row(n) }
  end

  private

  def row(row_number)
    1.upto(row_number).map { |column_number| value(row_number, column_number) }
  end

  def value(row_number, column_number)
    return 0 if column_number < 1
    return 0 if column_number > row_number

    return 1 if row_number == 1

    left  = value(row_number.pred, column_number.pred)
    right = value(row_number.pred, column_number)
    left + right
  end
end
