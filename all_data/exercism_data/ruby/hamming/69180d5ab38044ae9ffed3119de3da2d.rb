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
      new(left.split(''), right.split('')).compute
    end
  end
end
