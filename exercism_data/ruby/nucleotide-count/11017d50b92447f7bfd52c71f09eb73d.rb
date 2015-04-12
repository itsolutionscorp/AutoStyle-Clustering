class DNA
  DNA_NUCLEOTIDES = ["A", "C", "T", "G"]
  RNA_ONLY_NUCLEOTIDE = "U"

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    return 0 if nucleotide == RNA_ONLY_NUCLEOTIDE
    raise ArgumentError unless DNA_NUCLEOTIDES.include?(nucleotide)

    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, nucleotide_counts|
      nucleotide_counts[nucleotide] = count(nucleotide)
    end
  end
end