class Complement
  PAIRS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  REVERSE_PAIRS = PAIRS.invert

  def self.of_dna sequence
    sequence.chars.each_with_object('') { |c, a| a << PAIRS[c] }
  end

  def self.of_rna sequence
    sequence.chars.each_with_object('') { |c, a| a << REVERSE_PAIRS[c] }
  end
end
