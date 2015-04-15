module Complement
  DNA_COMPLEMENTS = { 'G' => 'C',
                      'C' => 'G',
                      'T' => 'A',
                      'A' => 'U' }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(strand)
    transcribe(strand, DNA_COMPLEMENTS)
  end

  def self.of_rna(strand)
    transcribe(strand, RNA_COMPLEMENTS)
  end

  private

  def self.transcribe(strand, complements_hash)
    trans = strand.split('').map { |nuc| complements_hash[nuc] }
    trans.join
  end
end
