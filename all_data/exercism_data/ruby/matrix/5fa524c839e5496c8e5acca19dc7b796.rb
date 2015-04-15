class Matrix

  attr_reader :rows, :columns

  def initialize(string)
    @rows    = string.lines.map { |row| row.split.map(&:to_i) }
    @columns = @rows.transpose
  end

end
