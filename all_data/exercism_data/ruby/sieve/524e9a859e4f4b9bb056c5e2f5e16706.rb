class Sieve
  def initialize(num)
    @num = num
  end

  def primes()
    nums = (2..@num).to_a
    iend = nums.size - 1
    sqrt = Math.sqrt(@num).to_i
    (0..(nums.index(sqrt))).each do |i|
      n = nums[i]    # n is either prime or nil
      (i + n).step(iend, n) { |j| nums[j] = nil } unless n.nil?
    end
    nums.compact     # eliminate nil entries
  end
end
