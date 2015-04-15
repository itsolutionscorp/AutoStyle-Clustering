class Matrix
  attr_reader :rows, :columns

  def initialize(lines)
    @rows = parse(lines)
    @columns = @rows.transpose
  end

  private
  def parse(lines)
    lines.split("\n").map do |line|
      line.split.map(&:to_i)
    end
  end
end
