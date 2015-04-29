class Hamming
  # Will raise exceptions on errors
  def self.compute!(strand1, strand2)
    raise StrandsNotEqualLengths unless strand1.length == strand2.length

    compute(strand1, strand2)
  end

  def self.compute(strand1, strand2)
    (0...[strand1.length, strand2.length].min).count do |strand_position|
      strand1[strand_position] != strand2[strand_position]
    end
  end

  # Inputed strands not equal lengths
  class StrandsNotEqualLengths < StandardError; end
end
