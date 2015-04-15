class Hamming
  attr_reader :stream

  def initialize(left_stream, right_stream)
    @stream = left_stream.zip(right_stream)
  end

  def compute
    stream.count { |left, right| left != right }
  end

  class << self
    def compute(left_dna, right_dna)
      new(left_dna.chars, right_dna.chars).compute
    end
  end
end
