module Hamming
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
    short, *rest = @formatter.order(@strands).by(:length)
    short.zip(*rest).count { |acids| different? acids }
  end

  def different?(acids)
    acids.uniq.size != 1
  end
end

class StrandFormat
  def initialize
    @strands = []
  end

  def order(strands)
    @strands = strands
    self
  end

  def by(order)
    @strands.sort_by(&order)
  end
end
