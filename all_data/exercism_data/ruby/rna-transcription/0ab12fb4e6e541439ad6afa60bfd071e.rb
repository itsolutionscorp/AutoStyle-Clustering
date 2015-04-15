module Complement
  def self.of_dna(nucleotides)
    nucleotides.tr('GCTA', 'CGAU')
  end

  def self.of_rna(nucleotides)
    nucleotides.tr('CGAU', 'GCTA')
  end
end
