class Complement
  TRANSLATION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map { |c| TRANSLATION[c] || raise(ArgumentError) }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |c| TRANSLATION.key(c) || raise(ArgumentError) }.join
  end
end
