class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = rows_from_string(string)
  end

  def columns
    @columns ||= rows.transpose
  end

  private

  def rows_from_string(string)
    string.split("\n").map(&method(:array_from_line))
  end

  def array_from_line(line)
    line.scan(/\d+/).map(&:to_i)
  end
end
