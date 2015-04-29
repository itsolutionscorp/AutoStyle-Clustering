class DNA

  NUCLEOTIDES = %w(A C G T U)
  DNA_NUCLEOTIDES = NUCLEOTIDES - ['U']
  VALID_DNA_SEQUENCE_PATTERN = /\A[#{DNA_NUCLEOTIDES.join('')}]*\z/

  def initialize(sequence)
    raise ArgumentError unless sequence =~ VALID_DNA_SEQUENCE_PATTERN
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include? nucleotide
    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts()
    zero_dna_nucleotide_count.merge( element_counts )
  end

  private

    def zero_dna_nucleotide_count
      Hash[DNA_NUCLEOTIDES.map { |nucleotide| [nucleotide, 0] }]
    end

    def element_counts
      Hash[
        @sequence.chars.
        group_by { |char| char }.
        map { |char, chars| [char, chars.count]  }
      ]
    end
  
end
