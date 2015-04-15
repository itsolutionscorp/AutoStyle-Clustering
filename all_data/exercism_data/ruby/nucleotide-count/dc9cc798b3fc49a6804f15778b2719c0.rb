class DNA

  def initialize(codons)
    raise ArgumentError, "Contains invalid DNA nucleotide" unless codons.chars.all? {|c|Nucleotide.dna_member?(c)}
    @codons = codons
  end

  def count(nucleotide)
    raise ArgumentError, "Contains invalid nucleotides" unless Nucleotide.base_member?(nucleotide)
    @codons.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide.each_dna_nucleotide.with_object({}) do |nucleotide, dna_counts|
      dna_counts[nucleotide] = count(nucleotide)
    end
  end

end

class Nucleotide
  BASES = [
    ADENINE  = "A",
    CYTOSINE = "C",
    GUANINE  = "G",
    THYMINE  = "T",
    URIDINE  = "U"
  ]
  DNA = BASES - [URIDINE]

  def self.each_dna_nucleotide
    DNA.each
  end

  def self.base_member?(member)
    BASES.include?(member)
  end

  def self.dna_member?(member)
    DNA.include?(member)
  end
end
