class Sieve
  attr_reader :n
  def initialize(n)
    @n = n
  end
  def primes
    nums = [nil, nil, *2..n]
    (2..Math.sqrt(n)).each do |i|
    (i**2..n).step(i){|m| nums[m] = nil}  if nums[i]
  end
  nums.compact
  end
end
