class Hamming

  def self.compute(sequence1, sequence2)
    comparisons = sequence1.chars.zip(sequence2.chars)
    comparisons.inject(0) do |differences, comparison|
      differences += calculate_difference(comparison)
    end
  end

  def self.calculate_difference(comparison)
    return 0 if comparison.any?(&:nil?)
    comparison.first == comparison.last ? 0 : 1
  end

end
