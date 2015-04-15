class DNA
  def initialize(dna_string)
    @dna_string = dna_string.split("")
    @dna_nucleotides = ["A", "C", "T", "G"]
  end

  def count(nucleotide)
    return 0 if nucleotide == "U"
    raise ArgumentError unless @dna_nucleotides.include?(nucleotide)

    @dna_string.select do |nucleotide_in_dna_string|
      nucleotide_in_dna_string == nucleotide
    end.size
  end

  def nucleotide_counts
    @dna_nucleotides.each_with_object({}) do |nucleotide, nucleotide_counts|
      nucleotide_counts[nucleotide] = count(nucleotide)
    end
  end
end
