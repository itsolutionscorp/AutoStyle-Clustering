class Matrix

  attr_reader :rows

  def initialize(string)
    @rows = convert(string)
  end

  def columns
    rows.transpose
  end

  def convert(string)
    input = string.split("\n")
    input.map { |row| row.split.map(&:to_i) }
  end

end
