class Matrix
  def initialize(matrix)
    @matrix = matrix
    @width = matrix.gsub(/\S/, "").index("\n") + 1
  end

  def rows
    @matrix.split(/\s+/).map(&:to_i).each_slice(@width).to_a
  end

  def columns
    @matrix.split(/\s+/).each_with_index.with_object(Hash.new([])) { |(n, i), h| h[i % @width] += [n.to_i] }
  end
end
