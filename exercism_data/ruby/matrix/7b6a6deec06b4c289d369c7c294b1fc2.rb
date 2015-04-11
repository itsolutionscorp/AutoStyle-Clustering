class Matrix
  def initialize(str)
    @str = str
    @table = build_table
  end

  def rows
    @table
  end

  def columns
    @table.transpose
  end

  private

  def build_table
    @str.split("\n").collect { |i| i.split.map(&:to_i) }
  end
end
