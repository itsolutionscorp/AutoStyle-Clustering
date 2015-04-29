class DNA
  NUCLEOTIDE_TYPES = %w{A C G T U}
  DNA_NUCLEOTIDE_TYPES = %w{A C G T}

  def initialize(dna_string)
    raise ArgumentError unless valid_dna_string? dna_string

    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide? nucleotide

    nucleotide_counts.fetch(nucleotide, 0)
  end

  def nucleotide_counts
    @nucleotide_counts ||= DNA_NUCLEOTIDE_TYPES.each_with_object({}) {|type, counts|
      counts[type] = @dna_string.count(type)
    }
  end

  private

  def valid_dna_string?(candidate)
    (candidate.chars.to_a - DNA_NUCLEOTIDE_TYPES).empty?
  end

  def valid_nucleotide?(candidate)
    NUCLEOTIDE_TYPES.include?(candidate)
  end
end
