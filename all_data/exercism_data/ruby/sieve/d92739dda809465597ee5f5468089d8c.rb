class Sieve
  attr_reader :primes

  def initialize n
    nums = [nil, nil, *2..n]
    (2..Math.sqrt(n)).select { |x| !nums[x].nil? }.each do |prime|
      (prime**2..n).step(prime) { |mult| nums[mult] = nil }
    end
    @primes = nums.compact
  end
end
