class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    calculate_distance(@strand.chars.zip(sequence.chars))
  end

  private

  def calculate_distance(nucleotide_pairings)
    nucleotide_pairings.inject(0) do |sum, pair|
      valid_pair?(pair) && substitution_required?(pair) ? sum + 1 : sum
    end
  end

  def valid_pair?(pair)
    !pair[0].nil? && !pair[1].nil?
  end

  def substitution_required?(pair)
    pair[0] != pair[1]
  end

end
