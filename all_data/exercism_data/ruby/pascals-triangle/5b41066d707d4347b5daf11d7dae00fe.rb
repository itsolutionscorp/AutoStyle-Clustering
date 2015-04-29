class Triangle
  def initialize(row_count)
    @row_count = row_count
  end

  def rows
    1.upto(@row_count).each_with_object([]) do |row_number, rows|
      rows << row(row_number)
    end
  end

private

  def row(number)
    case number 
    when 1 then [1]
    else [1, *create(row(number - 1)) ,1]
    end
  end

  def create(row)
    row.each_cons(2).map { |a, b| a + b }
  end
end
