class Matrix

  def initialize(string_numbers)
    @string_numbers = string_numbers
  end

  def rows
    matrix = @string_numbers.split("\n")
    matrix.map { |row| row.split.map(&:to_i) }
  end

  def columns
    columns = []
    rows.each do |row|
      row.each_with_index do |cell, index|
        columns[index] ||= []
        columns[index] << cell
      end
    end
    columns
  end
end
