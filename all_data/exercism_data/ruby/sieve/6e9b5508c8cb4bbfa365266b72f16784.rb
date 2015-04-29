class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    sift((2..@limit).to_a)
  end

  private

  def sift(nums, primes = [])
    return primes if nums.empty?
    sift(nums.drop(1).select { |x| x % nums.first != 0 }, primes.push(nums.first))
  end
end
