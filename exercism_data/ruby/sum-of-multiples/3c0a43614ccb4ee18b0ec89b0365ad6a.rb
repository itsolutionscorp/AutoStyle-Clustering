class SumOfMultiples
  def self.to(lim, nums = [3, 5])
    (nums.min...lim).to_a.select { |x| nums.any? { |n| x % n == 0 } }.reduce(:+) || 0
  end

  def initialize(*nums)
    @nums = nums
  end

  def to(lim)
    self.class.to(lim, @nums)
  end
end
