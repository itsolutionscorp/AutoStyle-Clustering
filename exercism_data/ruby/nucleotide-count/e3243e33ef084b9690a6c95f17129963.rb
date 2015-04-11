class NucleicAcid
  NUCLEOTIDES = %w(A C G T U)
end

class DNA < NucleicAcid
  NUCLEOTIDES = %w(A C G T)

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NucleicAcid::NUCLEOTIDES.include?(nucleotide)
    return 0 unless DNA::NUCLEOTIDES.include?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    nucleotides.each_with_object(empty_sequence_nucleotide_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def empty_sequence_nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = 0
    end
  end

  def nucleotides
    @sequence.chars
  end
end
