class DNA

  NUCLEOTIDES = %w[A T C G]
  NUCLEOTIDES_AND_URACIL = NUCLEOTIDES.dup.concat %w[U]

  def initialize(code)
    raise ArgumentError if invalid_dna(code)
    @code = code
  end

  def count(nucleotide)
    raise ArgumentError if invalid_nucleotide(nucleotide)
    nucleotide_counts.fetch(nucleotide) { 0 }
  end

  def nucleotide_counts
    @nucleotide_counters = Hash[NUCLEOTIDES.zip([0]*NUCLEOTIDES.size)]
    @code.chars.each do |char|
      @nucleotide_counters[char] += 1
    end
    @nucleotide_counters
  end

  private

  def invalid_dna(code)
    return false if code.chars.empty?
    code.chars.any? { |c| !NUCLEOTIDES.include?(c) }
  end

  def invalid_nucleotide(nucleotide)
    !NUCLEOTIDES_AND_URACIL.include? nucleotide
  end

end
