class Matrix
  def initialize(matrix_str)
    @matrix_str = matrix_str
  end

  def rows
    matrix_str.each_line.map { |line| line.split.map(&:to_i) }
  end

  def columns
    rows.transpose
  end

  private

  attr_reader :matrix_str
end
