class Hamming
  class << self
    def compute(a, b)
      Strand.new(a).distance(Strand.new(b))
    end
  end
end

class Strand
  attr_reader :_strand

  def initialize(strand)
    @_strand = strand.split('')
  end

  def distance(other)
    zip(other).map { |a, b| score(a, b) }.reduce(:+)
  end

  private

  def score(a, b)
    a == b ? 0 : 1
  end

  def zip(other)
    _strand.zip(other._strand).reject { |zipped| zipped.include?(nil) }
  end
end
