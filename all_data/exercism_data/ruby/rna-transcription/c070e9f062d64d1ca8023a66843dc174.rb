class Complement
  DNA_NUCLEOTIDES_COMPLEMENTS = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  RNA_NUCLEOTIDES_COMPLEMENTS = DNA_NUCLEOTIDES_COMPLEMENTS.invert
  
  def self.of_dna(dna_strand)
    dna_strand.chars.map { |nucleotide| Complement.of_dna_nucleotide(nucleotide) }.join
  end
  def self.of_rna(rna_strand)
    rna_strand.chars.map { |nucleotide| Complement.of_rna_nucleotide(nucleotide) }.join
  end

  def self.of_dna_nucleotide(dna_nucleotide)
    DNA_NUCLEOTIDES_COMPLEMENTS[dna_nucleotide]
  end
  def self.of_rna_nucleotide(rna_nucleotide)
    RNA_NUCLEOTIDES_COMPLEMENTS[rna_nucleotide]
  end

end
