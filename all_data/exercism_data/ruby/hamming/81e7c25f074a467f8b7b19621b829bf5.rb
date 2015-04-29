class Hamming

  def self.compute(dna1, dna2)
    grouped_nucleotides = dna1.chars.zip(dna2.chars)

    grouped_nucleotides.inject(0) do |counter, group|
      counter += calculate_difference(group)
    end
  end

  private

  def self.calculate_difference(comparison)
    return 0 if comparison.any?(&:nil?)

    comparison.first == comparison.last ? 0 : 1
  end
end
