class DNA
  attr_reader :strand

  VALID_NUCLEOTIDES = ["A", "C", "G", "T"]

  def initialize(strand)
    @strand = strand
    raise ArgumentError if invalid_strand?
  end

  def count(nucleotide_to_count)
    raise ArgumentError if invalid_nucleotide?(nucleotide_to_count)
    strand.chars.count { |nucleotide| nucleotide == nucleotide_to_count }
  end

  def nucleotide_counts
    VALID_NUCLEOTIDES.reduce({}) do |counts, nucleotide|
      counts[nucleotide] = count(nucleotide)
      counts
    end
  end

  private

  def invalid_strand?
    strand.chars.find do |candidate|
      invalid_nucleotide?(candidate)
    end
  end

  def invalid_nucleotide?(candidate)
    !VALID_NUCLEOTIDES.include?(candidate)
  end
end
