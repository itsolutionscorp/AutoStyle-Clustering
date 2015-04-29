class DNA
  NUCLEOTIDES = %w[A T C G]

  def initialize(nucleotides)
    raise ArgumentError unless valid_dna?(nucleotides)
    @nucleotides = nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless valid_dna? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @nucleotide_counts ||= count_nucleotides
  end

  private

  def valid_dna?(nucleotides)
    nucleotides.chars.all? do |nucleotide|
      NUCLEOTIDES.member?(nucleotide)
    end
  end

  def count_nucleotides
    frequencies = {}
    NUCLEOTIDES.each do |nucleotide|
      frequencies[nucleotide] = 0
    end
    @nucleotides.chars.each do |nucleotide|
      frequencies[nucleotide] += 1
    end
    frequencies
  end
end
