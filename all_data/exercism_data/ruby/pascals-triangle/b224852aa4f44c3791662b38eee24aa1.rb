class Triangle
  def initialize(row_count)
    @row_count = row_count
  end

  def rows
    [first_row].tap do |rows|
      (@row_count - 1).times { add_row rows }
    end
  end

private

  def add_row(rows)
    rows << row_after(rows.last)
  end

  def first_row
    [1]
  end

  def row_after(row)
    [1, *row.each_cons(2).map { |a, b| a + b }, 1]
  end
end
