class SumOfMultiples
  def self.to limit
    new(3, 5).to limit
  end

  def initialize *nums
    @nums = nums
  end

  def to limit
    (1...limit).to_a.keep_if do |i|
      @nums.any? { |x| i % x == 0 }
    end.inject(0, :+)
  end
end
