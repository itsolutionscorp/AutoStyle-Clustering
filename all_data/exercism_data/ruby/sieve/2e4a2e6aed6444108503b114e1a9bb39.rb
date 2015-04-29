class Sieve

  def initialize(max)
    @max = max
  end

  def primes
    nums = [nil, nil, *2..@max]
    (2..Math.sqrt(@max)).each do |i|
      (i**2..@max).step(i){|m| nums[m] = nil} if nums[i]
    end
    nums.compact
  end

end
