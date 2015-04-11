class Matrix
  def initialize(str)
    @str = str
  end

  def rows
    @str.split("\n").map { |e| e.split(' ').map(&:to_i) }
  end

  def columns
    rows.transpose
  end

  def saddle_points
    rows.each_with_index.inject([]) do |points, (row, i)|
      row.each_with_index do |e, j|
        if biggest_in_row(row, e) && lowest_in_column(columns[j], e)
          points << [i,j]
        end
      end
      points
    end
  end

  private

  def biggest_in_row(row, element)
    row.max == element
  end

  def lowest_in_column(column, element)
    column.min == element
  end
end
