module Complement
  DNA_COMPLEMENTS = { 'G' => 'C',
                  'C' => 'G',
                  'T' => 'A',
                  'A' => 'U' }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna(strand)
    trans = strand.split('').map { |nuc| DNA_COMPLEMENTS[nuc] }
    trans.join
  end

  def self.of_rna(strand)
    trans = strand.split('').map { |nuc| RNA_COMPLEMENTS[nuc] }
    trans.join
  end
end
