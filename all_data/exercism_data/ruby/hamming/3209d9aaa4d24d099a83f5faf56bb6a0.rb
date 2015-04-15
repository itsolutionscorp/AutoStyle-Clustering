class Hamming
  def self.compute(first, last)
    trim(first, last).transpose.count { |a,b| a != b }
  end

  def self.trim(*strands)
    strands.map { |strand| strand.chars.first(strands.min_by(&:size).size) }
  end
end
