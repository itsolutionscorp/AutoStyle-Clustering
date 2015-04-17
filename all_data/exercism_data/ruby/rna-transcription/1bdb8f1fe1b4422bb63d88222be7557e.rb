module Complement
  extend self

  def of_dna(strand)
    transcibe strand, from_dna_to_rna
  end

  def of_rna(strand)
    transcibe strand, from_rna_to_dna
  end

  private

  def transcibe(strand, transcription)
    strand.tr(*transcription)
  end

  def from_dna_to_rna
    ['GCTA', 'CGAU']
  end

  def from_rna_to_dna
    from_dna_to_rna.reverse
  end
end