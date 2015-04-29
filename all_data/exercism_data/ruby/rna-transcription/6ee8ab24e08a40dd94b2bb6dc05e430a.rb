module Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert

  def self.of_dna strand
    strand.split('').map { |base|
      DNA_COMPLEMENTS[base]
    }.join
  end

  def self.of_rna strand
    strand.split('').map { |base|
      RNA_COMPLEMENTS[base]
    }.join
  end
end
