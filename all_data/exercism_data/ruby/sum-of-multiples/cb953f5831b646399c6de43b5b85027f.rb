class SumOfMultiples
  DEFAULT_MULTIPLES = [3, 5]

  def self.to(max)
    new(*DEFAULT_MULTIPLES).to(max)
  end

  def initialize(*multiples_of)
    @multiples_of = multiples_of
  end

  def to(max)
    (1...max).select { |i| multiple?(i) }.inject(0, :+)
  end

  private

  def multiple?(candidate)
    @multiples_of.any? { |m| (candidate % m).zero? }
  end
end
