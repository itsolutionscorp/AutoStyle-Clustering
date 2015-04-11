class Matrix
  attr_reader :rows

  def initialize(matrix)
    @rows = parse matrix
  end

  def columns
    rows.transpose
  end

private

  def parse(matrix)
    matrix.split("\n").map { |row| row.split.map(&:to_i) }
  end

end
