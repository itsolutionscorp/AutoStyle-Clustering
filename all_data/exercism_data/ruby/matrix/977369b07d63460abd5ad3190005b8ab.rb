class Matrix
  attr_reader :rows
  def initialize(input)
    @rows = parse_input(input)
  end

  def columns
    rows.transpose
  end

  private
  def parse_input(input)
    input.split("\n").map do |line|
      line.split(" ").map(&:to_i)
    end
  end
end
