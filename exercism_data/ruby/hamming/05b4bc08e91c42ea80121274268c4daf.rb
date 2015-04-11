class Hamming
  class << self

    def initialize
      @dna = nil
    end

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
      result = 0

      @dna.times do |i|
        result += 1  if @dna.strand1[i] != @dna.strand2[i]
      end

      result
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

    strand1_size, strand2_size = @strand1.size, @strand2.size

    if strand1_size > strand2_size
      @strand1 = @strand1[0...strand2_size]
    else
      @strand2 = @strand2[0...strand1_size]
    end
  end
  
  def times &block
    strand1.size.times &block
  end

end
