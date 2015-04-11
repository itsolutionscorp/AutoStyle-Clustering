class Hamming

  # Single Responsability: Calculate hamming difference between to DNA strangs

  def self.compute(sequence_a, sequence_b)
    hamming_difference = 0
    (0 ... commun_length(sequence_a, sequence_b)).each do |i|
      hamming_difference += 1 if (sequence_a[i] != sequence_b[i])
    end
    return hamming_difference
  end

  private

  def self.commun_length(sequence_a, sequence_b)
    [sequence_a, sequence_b].map(&:length).min
  end
end
