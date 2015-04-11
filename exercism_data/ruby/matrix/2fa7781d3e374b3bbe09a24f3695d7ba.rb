class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = string.split("\n").map { |row| row.split.map(&:to_i) }
  end

  def columns
    @rows.transpose
  end
end
