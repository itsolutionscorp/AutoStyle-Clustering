module Complement
  def self.of_dna(sequence)
    raise ArgumentError if !sequence.index(/[GCTA]/)
    sequence.tr('GCTA', 'CGAU')
  end

  def self.of_rna(sequence)
    raise ArgumentError if !sequence.index(/[CGAU]/)
    sequence.tr('CGAU', 'GCTA')
  end
end
