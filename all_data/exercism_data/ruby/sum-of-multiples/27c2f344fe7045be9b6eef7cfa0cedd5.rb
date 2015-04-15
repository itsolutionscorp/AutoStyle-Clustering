class SumOfMultiples
  def self.to(max)
    new(3, 5).to(max)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(max)
    nums = []

    (1...max).each do |num|
      nums << num if multiples.any? { |multiple| num % multiple == 0 }
    end

    nums.reduce(0, :+)
  end

  private

  attr_reader :multiples
end
