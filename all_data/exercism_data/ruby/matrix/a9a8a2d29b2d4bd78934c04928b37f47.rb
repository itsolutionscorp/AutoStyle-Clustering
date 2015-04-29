class Matrix
  attr_reader :matrix

  def initialize(str)
    @matrix = str.split("\n").map { |r| r.split(' ').map(&:to_i) }
  end

  def rows
    matrix
  end

  def columns
    matrix.transpose
  end
end
