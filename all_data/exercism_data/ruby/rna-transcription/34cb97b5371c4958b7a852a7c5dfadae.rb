module Complement
  extend self

  def of_dna(strand)
    strand.tr *from_dna_to_rna
  end

  def of_rna(strand)
    strand.tr *from_rna_to_dna
  end

  private

  def from_dna_to_rna
    ['GCTA', 'CGAU']
  end

  def from_rna_to_dna
    from_dna_to_rna.reverse
  end
end
