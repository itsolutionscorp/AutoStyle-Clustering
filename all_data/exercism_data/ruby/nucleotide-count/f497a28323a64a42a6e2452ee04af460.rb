class DNA
  VALID_NUCLEOTIDES = {"A" => 0, "C" => 0, "G" => 0, "T" => 0}

  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    return 0 if nucleotide == "U"
    raise ArgumentError, "Invalid Nuclotide" unless VALID_NUCLEOTIDES[nucleotide]
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @nucleotide_counts ||= VALID_NUCLEOTIDES.merge(count_nucleotides)
  end

  private

  def count_nucleotides
    @sequence.
      chars.
      each_with_object(Hash.new(0)) do |nuc, memo| 
        memo[nuc] += VALID_NUCLEOTIDES[nuc] ? 1 : 0
      end
  end
end
