module Complement
  def self.of_dna(dna_strand)
    dna_strand.chars.map { |n| rna_nucleotide(n) }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |n| dna_nucleotide(n) }.join
  end


  DNA_TO_RNA = { 'G' => 'C','C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.rna_nucleotide(nucleotide)
    DNA_TO_RNA[nucleotide]
  end

  def self.dna_nucleotide(nucleotide)
    DNA_TO_RNA.invert[nucleotide]
  end
end
