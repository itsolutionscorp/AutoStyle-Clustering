class Matrix
  attr_reader :rows

  def initialize(matrix)
    @rows = matrix.lines.map { |line| line.split.map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end
end
