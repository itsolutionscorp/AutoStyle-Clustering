class Matrix

  attr_reader :string

  def initialize(string)
    @string = string
  end

  def columns
    rows.transpose
  end

  def rows
    string.lines.map { |line| line.split.map(&:to_i) }
  end

end
