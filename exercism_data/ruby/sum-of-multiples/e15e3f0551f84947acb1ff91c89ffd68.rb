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
      multiples.each do |multiple|
        nums << num if num % multiple == 0
      end
    end

    nums.uniq.reduce(0, :+)
  end

  private

  attr_reader :multiples
end
