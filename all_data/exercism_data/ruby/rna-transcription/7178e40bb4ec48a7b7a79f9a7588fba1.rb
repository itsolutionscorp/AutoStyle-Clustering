module Complement

  NUCLEOTIDE_MAP = { dna:'CGTA', rna:'GCAU' }

  def self.of_dna dna_string
    raise ArgumentError unless dna_string.match /[#{NUCLEOTIDE_MAP[:dna]}]+/
    dna_string.tr(*NUCLEOTIDE_MAP.values)
  end

  def self.of_rna rna_string
    raise ArgumentError unless rna_string.match /[#{NUCLEOTIDE_MAP[:rna]}]+/
    rna_string.tr(*NUCLEOTIDE_MAP.values.reverse)
  end
end
