class Matrix

  def initialize(matrix)
    @matrix = matrix
  end

  def rows
    @matrix.split("\n").map do |row|
      row.split(" ").map {|item| item.to_i }
    end
  end

  def columns
    results = []
    current_row = []
    rows.first.length.times.map do |col|
      rows.map do |row|
        current_row << row[col]
      end
      results << current_row
      current_row = []
    end
    results
  end

end
