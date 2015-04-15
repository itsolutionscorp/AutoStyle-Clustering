class Hamming

  def self.compute(strand_0, strand_1)
    hamming_distance(strand_0, strand_1)
  end

  def self.hamming_distance(strand_0,strand_1)
    calculate_distance(strand_0.chars.zip(strand_1.chars))
  end

  private

  def self.calculate_distance(nucleotide_pairings)
    nucleotide_pairings.inject(0) do |sum, pair|
      valid_pair?(pair) && substitution_required?(pair) ? sum + 1 : sum
    end
  end

  def self.valid_pair?(pair)
    !pair[0].nil? && !pair[1].nil?
  end

  def self.substitution_required?(pair)
    pair[0] != pair[1]
  end

end
