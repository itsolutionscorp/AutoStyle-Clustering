class Hamming
  def self.compute(strand1, strand2)
    new(strand1, strand2).compute
  end

  def initialize(strand1, strand2)
    @strand1 = strand1.chars
    @strand2 = strand2.chars
  end

  def compute
    return 0 if strand_sizes_are_different?
    differences.size
  end

  private

  attr_reader :strand1, :strand2

  def strand_sizes_are_different?
    strand1.size != strand2.size
  end

  def differences
    strand1.each_with_index.reject do |char, index|
      char == strand2[index]
    end
  end
end
