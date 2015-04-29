class Matrix
  attr_reader :rows

  def initialize(matrix_string)
    @matrix_string = matrix_string
  end

  def rows
    matrix = []
    @matrix_string.lines.each do |line|
      matrix << line.tr("\n","").split.map { |c| c.to_i }
    end
    return matrix
  end

  def columns
    self.rows.transpose
  end
end
