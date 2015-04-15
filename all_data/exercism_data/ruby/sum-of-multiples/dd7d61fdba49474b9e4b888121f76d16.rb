class SumOfMultiples
  def self.to(max)
    new(3, 5).to(max)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(max)
    (1...max).to_a.keep_if do |num|
      multiples.any? { |multiple| num % multiple == 0 }
    end.reduce(0, :+)
  end

  private

  attr_reader :multiples
end
