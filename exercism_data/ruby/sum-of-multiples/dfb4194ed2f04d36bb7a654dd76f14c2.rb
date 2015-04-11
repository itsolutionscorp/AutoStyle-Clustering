class SumOfMultiples
  attr_accessor :nums

  def self.to n
    new(3, 5).to n
  end

  def initialize *nums
    self.nums = nums
  end

  def to n
    (1...n).select { |m| nums.any? { |p| m % p == 0  } }.inject(0, &:+)
  end
end
