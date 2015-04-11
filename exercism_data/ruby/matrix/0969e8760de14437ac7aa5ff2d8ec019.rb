class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = parse_rows(string)
  end

  def columns
    @columns ||= rows.transpose
  end

  private

  def parse_rows(string)
    string.split("\n").map do |row|
      row.split(" ").map(&:to_i)
    end
  end
end
