class Matrix
  def initialize(string)
    @string = string
  end

  def rows
    @string.lines.map { |line| line.split.map(&:to_i) }
  end

  def columns
    rows.transpose
  end
end
