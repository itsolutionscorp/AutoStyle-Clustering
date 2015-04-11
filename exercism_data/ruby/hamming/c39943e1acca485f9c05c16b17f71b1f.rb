class Hamming
  def self.compute(strand1, strand2)
    strands_zipped = strand1.chars.zip(strand2.chars)
    strands_zipped.count{ |pair| pair[0] != pair [1] && !pair.any?(&:nil?)  }
  end
end
