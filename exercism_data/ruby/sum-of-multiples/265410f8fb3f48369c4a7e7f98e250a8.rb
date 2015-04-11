class SumOfMultiples
  def initialize *nums
    @nums = nums
  end

  def to limit
    self.class.to limit, @nums
  end

  def self.to limit, test_nums = [3, 5]
    (1...limit).to_a.keep_if do |i|
      test_nums.any? { |x| i % x == 0 }
    end.inject(0, &:+)
  end
end
