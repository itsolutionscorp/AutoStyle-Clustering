class DNA
  NUCLEOTIDES = %w{A T C G}
  VALID_NUCLEOTIDES = NUCLEOTIDES + %w{U}

  def initialize sequence
    @sequence = sequence

    raise ArgumentError unless valid_dna_sequence?
  end

  def count nucleotide
    raise ArgumentError unless valid_nucleotide? nucleotide

    nucleotide_counts[nucleotide] || 0
  end

  def nucleotide_counts
    counts = Hash[NUCLEOTIDES.zip [0].cycle]

    @sequence.chars.each_with_object counts do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def valid_dna_sequence?
    (@sequence.chars - NUCLEOTIDES).empty?
  end

  def valid_nucleotide? nucleotide
    VALID_NUCLEOTIDES.include? nucleotide
  end
end
