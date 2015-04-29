class String
  def nucleotides
    split //
  end
end

class Hamming
  def self.compute(original, candidate)
    mismatched_pairs(original, candidate).count
  end

  def self.mismatched_pairs(left, right)
    paired_strands(left, right).select do |pair|
      pair.first != pair.last
    end
  end

  def self.paired_strands(a, b)
    a.nucleotides.zip(b.nucleotides)
  end
end
