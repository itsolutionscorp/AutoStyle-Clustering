class SumOfMultiples
  class << self
    def instance
      @instance ||= self.new(3,5)
    end
    def to(max)
      instance.to(max)
    end
  end

  attr_reader :multiples

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(max)
    divisible_by_multiples(max).inject(&:+) || 0
  end

  private

  def divisible_by_multiples(max)
    1.upto(max-1).select { |n| divisible_by_multiples?(n) }
  end

  def divisible_by_multiples?(n)
    multiples.detect { |m| n % m == 0 }
  end
end
