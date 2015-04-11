class SumOfMultiples
  def self.to(limit)
    self.new.to(limit)
  end

  def initialize(*args)
    @multiples = args.empty? ? [3,5] : args
  end

  def to(limit)
    (1...limit).reduce(0) do |sum, i|
      @multiples.any?{|val| i % val == 0 } ? (sum + i) : sum
    end
  end
end
