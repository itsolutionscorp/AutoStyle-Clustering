class Hamming
  class << self
    # @dna => keeps each of strands
    attr_reader :strand1, :strand2
    def compute strand1, strand2

      @dna = DNA.new strand1, strand2
      if @dna.empty?
        0
      else
        hamming_difference
      end

    end

    private

    def hamming_difference

      @dna.count do |i|
        1  if @dna.strand1[i] != @dna.strand2[i]
      end

    end

  end
end

## DNA CLASS
class DNA
  attr_reader :strand1, :strand2

  def initialize strand1, strand2
    @strand1, @strand2 = strand1, strand2

    unify
  end

  def empty?
    @strand1.empty? && @strand2.empty?
  end

  def unify
    arr = [@strand1, @strand2]
    @strand1 = arr.min
    @strand2 = arr.max[0...@strand1.size]
  end

  def count &block
    strand1.size.times.count &block
  end

end
