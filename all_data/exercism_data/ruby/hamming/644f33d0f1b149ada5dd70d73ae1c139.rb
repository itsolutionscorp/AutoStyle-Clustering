class Hamming
  def self.compute(*strands)
    validate strands
    calculator = HammingCalculator.new strands
    calculator.distance
  end

  def self.validate(strands)
    raise ArgumentError, 'At least 2 strands required' if strands.length < 2
  end
  private_class_method :validate
end

class HammingCalculator
  def initialize(strands)
    @strands   = strands.map(&:chars)
    @formatter = StrandFormat.new
  end

  def distance
    short, *rest = @formatter.align @strands
    short.zip(*rest).count { |acids| different? acids }
  end

  def different?(acids)
    acids.uniq.size != 1
  end
end

class StrandFormat
  def align(strands)
    strands.sort_by(&:length)
  end
end
