class Matrix
  def initialize(diagram)
    @diagram = diagram
  end

  def rows
    @rows ||= get_rows
  end

  def columns
    @columns ||= get_columns
  end

  private
  def get_rows
    @diagram.split("\n").each_with_object([]) do |string_row, rs|
      rs << string_row.split.each_with_object([]) do |number, row|
        row << number.to_i
      end
    end
  end

  def get_columns
    rows[0].length.times.each_with_object([]) do |col_number, cols|
      cols << rows.each_with_object([]) do |row, col|
        col << row[col_number]
      end
    end
  end
end
