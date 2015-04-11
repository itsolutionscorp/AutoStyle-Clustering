module Complement
  DNA = 'GCTA'.chars
  RNA = 'CGAU'.chars

  DNA_COMPLEMENT = Hash[DNA.zip(RNA)]
  RNA_COMPLEMENT = Hash[RNA.zip(DNA)]

  def self.map(sequence, complement)
    (sequence.chars.map{|n| complement[n]}).join
  end

  def self.of_dna(nucleotide)
    map nucleotide, DNA_COMPLEMENT
  end

  def self.of_rna(nucleotide)
    map nucleotide, RNA_COMPLEMENT
  end
end
