class DNA

  NUCLEOTIDES = %w(A C G T U).freeze
  DNA_NUCLEOTIDES = %w(A C G T).freeze

  def initialize initial_sequence
    valid_or_raise_exception initial_sequence, :dna

    @dna_sequence = initial_sequence
  end

  def count sequence
    valid_or_raise_exception sequence, :rna

    return @dna_sequence.count sequence
  end

  def nucleotide_counts
    nucleotides = Hash.new

    DNA_NUCLEOTIDES.each do |char|
      nucleotides[char] = @dna_sequence.count char
    end

    return nucleotides
  end

  private
    def valid_or_raise_exception sequence, type

      case type
        when :rna
          regexp = /[^#{NUCLEOTIDES}]/
        when :dna
          regexp = /[^#{DNA_NUCLEOTIDES}]/
      end

      if !!(regexp =~ sequence)
        raise ArgumentError
      end

    end

end
