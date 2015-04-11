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
    nucleotide_counts = Hash.new(0)
    dna_nucleotides.each do |nucleotide|
      nucleotide_counts[nucleotide] = strand.count(nucleotide)
    end
    nucleotide_counts
  end

  def count(nucleotide)
    raise ArgumentError, 'Argument is not a valid nucleotide' unless nucleotides.include?(nucleotide)
    strand.count(nucleotide)
  end

  private

  def dna_nucleotides
    [ADENINE, CYTOSINE, GUANINE, THYMINE]
  end

  def nucleotides
    [ADENINE, CYTOSINE, GUANINE, THYMINE, URACIL]
  end
end
