class Hamming
  class Pair < Struct.new(:a, :b)
    def different?
      return false if a.nil?
      return false if b.nil?

      a != b
    end
  end

  def initialize(sequence_1, sequence_2)
    @pairs = sequence_1.zip(sequence_2).map { |a, b| Pair.new(a, b) }
  end

  def compute
    @pairs.count(&:different?)
  end

  def self.compute(strand_1, strand_2)
    new(strand_1.chars, strand_2.chars).compute
  end
end
