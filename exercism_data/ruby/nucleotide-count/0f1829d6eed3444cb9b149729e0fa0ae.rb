class DNA
  VALID_NUCLEOTIDES = ['A', 'G', 'C', 'T']
  def initialize(nucleotide_sequence)
    raise ArgumentError, "Invalid nucleotide_sequence #{nucleotide_sequence}" if /[^AGCT]/.match(nucleotide_sequence)
    @nucleotide_sequence = nucleotide_sequence
  end

  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide #{nucleotide}" unless VALID_NUCLEOTIDES.include?(nucleotide)
    @nucleotide_sequence.chars.count(nucleotide)
  end

  def nucleotide_counts
    VALID_NUCLEOTIDES.each_with_object({}) do |nucleotide, nucleotide_count|
      nucleotide_count.merge!({nucleotide => count(nucleotide)})
    end
  end
end
