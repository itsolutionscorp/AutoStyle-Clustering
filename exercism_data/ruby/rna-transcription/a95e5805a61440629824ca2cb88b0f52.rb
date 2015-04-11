class Complement
  @@dna_nucleotides = 'GCTA'
  @@rna_nucleotides = 'CGAU'

  def self.of_dna(nucleotide)
    nucleotide.tr(@@dna_nucleotides, @@rna_nucleotides)
  end

  def self.of_rna(nucleotide)
    nucleotide.tr(@@rna_nucleotides, @@dna_nucleotides)
  end
end
