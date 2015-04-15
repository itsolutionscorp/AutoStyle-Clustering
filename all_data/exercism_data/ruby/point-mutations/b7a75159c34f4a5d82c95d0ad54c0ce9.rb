class DNA
  def initialize(normal)
    @normal = normal
  end

  def hamming_distance(variant)
    HammingDistance.calculate(normal, variant)
  end

  private
  attr_reader :normal
end

class HammingDistance
  def initialize(normal, variant)
    @normal, @variant = normal, variant
  end

  def self.calculate(normal, variant)
    new(normal, variant).calculate
  end

  def calculate
    variant.zip(normal).select{ |v, n| !n.nil? && n != v }.size
  end

  private

  def normal
    @normal.chars
  end

  def variant
    @variant.chars
  end
end
