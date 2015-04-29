class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(other_strand)
    comparison = @strand.zip(other_strand.chars).reject{|c| c.any?(&:nil?)}
    hamming = 0
    comparison.compact.each do |str, other|
      if str != other
        hamming += 1
      end
    end
    hamming
  end
end
