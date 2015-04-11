class DNA

  NUCLEOTIDES = %w(A C G T U)
  DNA_NUCLEOTIDES = NUCLEOTIDES - ['U']

  def initialize(sequence)
    raise ArgumentError unless valid_dna?(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
    nucleotide_counts.fetch(nucleotide) { 0 }
  end

  def nucleotide_counts()
    element_counts
  end

  private

    def valid_dna?(sequence)
      sequence.chars.all? { |char| DNA_NUCLEOTIDES.include? char }
    end

    def element_counts
      Hash[
        DNA_NUCLEOTIDES.map { |nucleotide| [nucleotide, @sequence.count(nucleotide)] }
      ]
    end
  
end
