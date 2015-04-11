class DNA
  ADENINE  = 'A'
  THYMINE  = 'T'
  CYTOSINE = 'C'
  GUANINE  = 'G'
  URACIL   = 'U'
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def nucleotide_counts
    nucleotide_counts = dna_nucleotides.inject({}) do |nucleotide_counts, nucleotide|
      nucleotide_counts[nucleotide] = string.count(nucleotide)
      nucleotide_counts
    end
  end

  def count(nucleotide)
    raise ArgumentError, 'Argument is not a valid nucleotide' unless nucleotides.include?(nucleotide)
    string.count(nucleotide)
  end

  private

  def dna_nucleotides
    [ADENINE, CYTOSINE, GUANINE, THYMINE]
  end

  def nucleotides
    [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  end
end
