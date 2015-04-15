class Sieve
  attr_reader :limit
  def initialize num
    @limit = num
    @nums = (2..@limit).map { |n| [n,0] }.to_h
  end

  def primes
    p = 2
    (p..@limit).each do |i|
      @nums.select { |n| @nums[n] = 1 if (n % i).zero? and n != i }
    end
    @nums.reject { |k,v| v.nonzero? }.keys
  end
end
