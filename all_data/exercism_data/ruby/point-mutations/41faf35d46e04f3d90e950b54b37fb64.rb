class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    calculate_distance_between coupled_nucleoties(sequence)
  end

  private

  def coupled_nucleotides(sequence)
    @strand.chars.zip(sequence.chars)
  end

  def calculate_distance_between(nucleotide_pairings)
    nucleotide_pairings.inject(0) do |sum, pair|
      increment_distance?(pair) ? sum + 1 : sum
    end
  end

  def increment_distance?(pair)
    valid_pair?(pair) && substitution_required?(pair)
  end

  def valid_pair?(pair)
    !pair[0].nil? && !pair[1].nil?
  end

  def substitution_required?(pair)
    pair[0] != pair[1]
  end

end
