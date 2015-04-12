class Hamming
  def compute(*strands)
    strands.map(&:chars).transpose.count { |a, b| a != b }
  end
end
