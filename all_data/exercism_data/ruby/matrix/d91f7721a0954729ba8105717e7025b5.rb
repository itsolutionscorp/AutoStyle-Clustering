class Matrix
  def initialize(input)
    @raw_data = input
  end

  def rows
    @rows ||= @raw_data.lines.map { |r| r.split(" ").map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end
end
