class Hamming

  def self.compute(sequence, alt_sequence)
    nucleotide_pairs(sequence, alt_sequence).count {|x, y| point_mutation?(x, y)}
  end

  def self.nucleotide_pairs(sequence, alt_sequence)
    sequence.chars.zip alt_sequence.chars
  end

  def self.point_mutation?(x, y)
    (x && y) && (x != y)
  end

end
