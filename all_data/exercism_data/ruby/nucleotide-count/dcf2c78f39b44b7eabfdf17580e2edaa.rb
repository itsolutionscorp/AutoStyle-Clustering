class DNA
  ADENINE  = 'A'
  THYMINE  = 'T'
  CYTOSINE = 'C'
  GUANINE  = 'G'
  URACIL   = 'U'
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def nucleotide_counts
    {
      ADENINE  => count(ADENINE),
      CYTOSINE => count(CYTOSINE),
      GUANINE  => count(GUANINE),
      THYMINE  => count(THYMINE),
    }
  end

  def count(nucleotide)
    raise ArgumentError, 'Argument is not a valid nucleotide' unless nucleotides.include?(nucleotide)
    strand.count(nucleotide)
  end

  private

  def nucleotides
    [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  end
end
