class Matrix
  attr_reader :rows, :columns

  def initialize(lines)
    @rows = parse(lines)
    @columns = @rows.transpose
  end

  def saddle_points
    [].tap do |points|
      (0 .. @rows.length - 1).each do |i|
        (0 .. @columns.length - 1).each do |j|
          value = @rows[i][j]
          points << [i, j] if saddle_row?(value, i) and saddle_column?(value, j)
        end
      end
    end
  end

  private
  def parse(lines)
    lines.split("\n").map do |line|
      line.split.map(&:to_i)
    end
  end

  def saddle_row?(value, idx)
    @rows[idx].select { |v| v <= value }.length == @rows[idx].length
  end

  def saddle_column?(value, idx)
    @columns[idx].select { |v| v >= value }.length == @columns[idx].length
  end
end
