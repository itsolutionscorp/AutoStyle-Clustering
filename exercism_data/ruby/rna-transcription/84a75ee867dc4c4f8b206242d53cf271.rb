class Complement
  PAIRS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(sequence)
    sequence.chars.inject('') { |memo, c| memo += PAIRS[c]; memo }
  end

  def self.of_rna(sequence)
    sequence.chars.inject('') { |memo, c| memo += PAIRS.invert[c]; memo }
  end
end
