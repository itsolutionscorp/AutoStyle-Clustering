class Matrix
  def initialize(string)
    @matrix = string.lines.map { |row| row.split.map(&:to_i) }
  end

  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end
end
