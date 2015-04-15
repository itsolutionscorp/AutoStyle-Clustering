class Hamming
  def self.compute(strand1, strand2)
    calculator = HammingCalculator.new [strand1, strand2]
    calculator.distance
  end
end

class HammingCalculator
  def initialize(strands)
    @strands   = strands.map(&:chars)
    @formatter = StrandFormat.new
  end

  def distance
    short, long = @formatter.align @strands
    short.zip(long).count { |a, b| a != b }
  end
end

class StrandFormat
  def align(strands)
    strands.sort_by(&:length)
  end
end
