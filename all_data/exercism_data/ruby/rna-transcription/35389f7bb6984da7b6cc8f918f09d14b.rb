module Complement
  NUCLOETIDES = {
    dna: 'GCTA',
    rna: 'CGAU'
  }

  def self.of_dna(dna)
    dna.tr(NUCLOETIDES[:dna], NUCLOETIDES[:rna])
  end

  def self.of_rna(rna)
    rna.tr(NUCLOETIDES[:rna], NUCLOETIDES[:dna])
  end
end
