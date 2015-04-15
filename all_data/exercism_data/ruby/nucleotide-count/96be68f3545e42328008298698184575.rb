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
    raise ArgumentError, "invalid nucleotide" unless Nucleotide.new(nucleotide).valid?
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide::DNA.each_with_object({}) do |nucleotide, tallies|
      tallies[nucleotide] = count(nucleotide)
    end
  end
end

class Nucleotide
  ADENINE  = "A"
  CYTOSINE = "C"
  GUANINE  = "G"
  THYMINE  = "T"
  URACIL   = "U"
  ALL = [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  DNA = ALL - [URACIL]

  def initialize(code)
    @code = code
  end

  def valid?
    ALL.include?(@code)
  end
end
