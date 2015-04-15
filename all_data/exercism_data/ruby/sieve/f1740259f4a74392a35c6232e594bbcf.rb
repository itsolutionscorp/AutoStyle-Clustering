class Sieve
  attr_reader :primes

  def initialize(upto)
    @primes = _eratosthenes(upto)
  end

  def _eratosthenes(n)
    # from http://rosettacode.org/wiki/Sieve_of_Eratosthenes#Ruby
    nums = [nil, nil, *2..n]
    (2..Math.sqrt(n)).each do |i|
      (i**2..n).step(i) { |m| nums[m] = nil }  if nums[i]
    end
    nums.compact
  end
end
