class Matrix
  def initialize(s)
    @matrix = s.split("\n").map { |x| x.scan(/\d+/).map(&:to_i) }
  end

  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end
end
