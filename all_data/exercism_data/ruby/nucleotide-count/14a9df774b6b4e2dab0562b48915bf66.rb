class DNA
  VALID_NUCLEOTIDES = %w(A C G T U)
  DNA_NUCLEOTIDES = VALID_NUCLEOTIDES - %w(U)
  VALID_SEQUENCE_REGEX = /[#{DNA_NUCLEOTIDES.join}]/

  attr_reader :sequence

  def initialize(sequence)
    raise ArgumentError if invalid_sequence?(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError if invalid_nucleotide?(nucleotide)
    sequence.downcase.scan(nucleotide.downcase).count
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, histogram|
      histogram[nucleotide] = count nucleotide
    end
  end

  private

  def invalid_sequence?(chain)
    !(chain.chars - DNA_NUCLEOTIDES).empty?
  end

  def invalid_nucleotide?(nucleotide)
    !VALID_NUCLEOTIDES.include?(nucleotide)
  end
end
