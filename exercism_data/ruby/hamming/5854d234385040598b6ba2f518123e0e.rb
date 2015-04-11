class Hamming

  def self.compute(sequence, mutation)
    nucleotide_pairs(sequence, mutation).count {|s, m| point_mutation?(s, m)}
  end

  def self.nucleotide_pairs(sequence, mutation)
    sequence.chars.zip mutation.chars
  end

  def self.point_mutation?(x, y)
    (x && y) && (x != y)
  end

end
