class DNA
  ADENINE  = "A"
  CYTOSINE = "C"
  GUANINE  = "G"
  THYMINE  = "T"
  URACIL   = "U"
  ALL_NUCLEOTIDES = [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  DNA_NUCLEOTIDES = ALL_NUCLEOTIDES - [URACIL]

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError, "invalid nucleotide" unless ALL_NUCLEOTIDES.include?(nucleotide)
    @sequence.scan(nucleotide).count
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
end
