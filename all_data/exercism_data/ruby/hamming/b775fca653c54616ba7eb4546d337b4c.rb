class Hamming
  # Computes the Hamming distance between two strands
  def self.compute(strand1, strand2)
    smaller_strand_size = [strand1.size, strand2.size].min
    (0...smaller_strand_size).count do |i|
      strand1[i] != strand2[i]
    end
  end
end
