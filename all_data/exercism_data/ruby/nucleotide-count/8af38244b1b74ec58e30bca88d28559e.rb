class DNA

  DNA_NUCLEOTIDES = %w{A C G T}
  VALID_NUCLEOTIDES = %w(A C G T U)

  attr_accessor :strand

  def initialize(strand)
    @strand_chars=strand.chars
    @strand_chars.each do |char|
      raise ArgumentError, 'DNA is not valid' unless DNA_NUCLEOTIDES.member?(char)
    end
  end

  def count(letter)
    #raise ArgumentError, "Nucleotide is not valid" if letter =~ INVALID_NUCLEOTIDE
    raise ArgumentError, "Nucleotides are not valid" unless VALID_NUCLEOTIDES.member? letter
    @strand_chars.count(letter)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide,number|
      number[nucleotide]=count(nucleotide)
    end

  end

end
