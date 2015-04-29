class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    other_bits = other_strand.chars

    count = 0
    bits.each_with_index do |bit, i|
      other_bit = other_bits[i]
      count += 1 if other_bit && other_bit != bit
    end
    count
  end

  private

  def bits
    @strand.chars
  end
end
