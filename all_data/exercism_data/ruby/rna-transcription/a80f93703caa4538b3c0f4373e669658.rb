class Complement
  DNA_NUCS = 'CGTA'
  RNA_NUCS = 'GCAU'
  def self.of_dna(strand)
    raise ArgumentError, "invalid nucleotide" if strand =~ /[^CGTA]/
    strand.tr(DNA_NUCS, RNA_NUCS)
  end
  def self.of_rna(strand)
    raise ArgumentError, "invalid nucleotide" if strand =~ /[^GCAU]/
    strand.tr(RNA_NUCS, DNA_NUCS)
  end
end
