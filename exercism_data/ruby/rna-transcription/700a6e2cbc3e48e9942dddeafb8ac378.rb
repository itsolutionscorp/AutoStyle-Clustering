class Complement
  DnaToRnaMapping = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  DNAChars = /[GCTA]/
  RNAChars = /[CGAU]/

  def self.of_dna(dna)
    dna.gsub(DNAChars, DnaToRnaMapping)
  end

  def self.of_rna(rna)
    rna.gsub(RNAChars, DnaToRnaMapping.invert)
  end
end
