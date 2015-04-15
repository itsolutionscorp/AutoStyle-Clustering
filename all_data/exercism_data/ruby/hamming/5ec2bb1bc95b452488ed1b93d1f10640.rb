class Hamming
  attr_reader :stream

  def initialize(left, right)
    @stream = left.zip(right)
  end

  def compute
    stream.count { |pair| pair[0] != pair[1] }
  end

  class << self
    def compute(left, right)
      new(left.chars, right.chars).compute
    end
  end
end
