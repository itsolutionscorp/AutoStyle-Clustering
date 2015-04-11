class DNA
  attr_reader :nucleotide_counts
  
  GUANINE  = 'G'
  ADENINE  = 'A'
  THYMINE  = 'T'
  CYTOSINE = 'C'
  URACIL   = 'U'

  ALL_NUCLEOTIDES = [ GUANINE, ADENINE, THYMINE, CYTOSINE, URACIL]
  DNA_NUCLEOTIDES = [ GUANINE, ADENINE, THYMINE, CYTOSINE ]

  def initialize(strand)
    @strand = strand
    @nucleotide_counts = DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    unless ALL_NUCLEOTIDES.include?(nucleotide)
      raise ArgumentError, "Expected one of #{ALL_NUCLEOTIDES}, but recieved \"#{nucleotide}\""
    end
    @strand.chars.count(nucleotide)
  end

end
