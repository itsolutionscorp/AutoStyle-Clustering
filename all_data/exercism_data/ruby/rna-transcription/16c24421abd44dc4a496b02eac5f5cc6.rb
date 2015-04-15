class Complement
  def self.of_dna(strand)
    complement_strand = ''
    strand.each_char do |nucleotide|
      complement_strand << Complement.of_dna_nucleotide(nucleotide)
    end
    complement_strand
  end

  def self.of_rna(strand)
    complement_strand = ''
    strand.each_char do |nucleotide|
      complement_strand << Complement.of_rna_nucleotide(nucleotide)
    end
    complement_strand
  end

  def self.of_dna_nucleotide(dna_nucleotide)
    complements = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
    raise ArgumentError unless complements.keys.include?(dna_nucleotide)
    complements[dna_nucleotide]
  end

  def self.of_rna_nucleotide(rna_nucleotide)
    complements = { 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T' }
    raise ArgumentError unless complements.keys.include?(rna_nucleotide)
    complements[rna_nucleotide]
  end
end
