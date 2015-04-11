class Complement
  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars
       .map { |c| RNA_COMPLEMENTS[c] }
       .join
  end

  def self.of_rna(rna)
    rna.chars
       .map { |c| RNA_COMPLEMENTS.key(c) }
       .join
  end
end
