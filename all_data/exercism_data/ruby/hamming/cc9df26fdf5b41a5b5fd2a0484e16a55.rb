class Hamming

  def self.compute(sequence1, sequence2)
    shorter, longer = [sequence1, sequence2].sort_by(&:length)
    comparisons = shorter.chars.zip(longer.chars)
    comparisons.count do |comparison|
      comparison.first != comparison.last
    end
  end

end
