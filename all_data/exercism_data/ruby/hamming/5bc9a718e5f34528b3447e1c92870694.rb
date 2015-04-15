class Hamming
  def self.compute(*strands)
    strands.map(&:chars).transpose.count { |a, b| a != b }
  end
end
