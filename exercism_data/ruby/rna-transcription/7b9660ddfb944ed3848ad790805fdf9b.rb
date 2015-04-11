# Rna transcription

class Complement
  def self.of_dna(dna)
    rna = dna.tr('GCTA', 'CGAU')
    rna
  end

  def self.of_rna(rna)
    dna = rna.tr('CGAU', 'GCTA')
    dna
  end
end
