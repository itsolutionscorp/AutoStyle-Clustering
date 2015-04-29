class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna(dna_string)
    (dna_string.chars.map { |e| DNA_COMPLEMENTS[e] }).join
  end

  def self.of_rna(rna_string)
    (rna_string.chars.map { |e| RNA_COMPLEMENTS[e] }).join
  end
end
