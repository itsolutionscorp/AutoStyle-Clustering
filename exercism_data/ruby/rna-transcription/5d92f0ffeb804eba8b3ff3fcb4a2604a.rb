class Complement
  def initialize(strand)
    @strand = strand
  end

  def complement_with(complements)
    nucleotides.inject("") do |complemented_strand, nucleotide|
      complemented_strand << complements[nucleotide]
    end
  end

  def nucleotides
    @strand.chars
  end

  def self.of_dna(nucleotides)
    new(nucleotides).complement_with(dna_complements)
  end

  def self.of_rna(nucleotides)
    new(nucleotides).complement_with(rna_complements)
  end

  def self.rna_complements
    { 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A' }
  end

  def self.dna_complements
    { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  end
end
