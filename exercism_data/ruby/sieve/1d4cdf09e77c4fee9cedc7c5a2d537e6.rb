class Sieve
  def initialize(limit)
    candidates = (2..limit).to_a
    @primes = []
    until candidates.empty?
      prime = candidates.shift
      candidates.reject! { |n| (n % prime).zero? }
      @primes << prime
    end
  end

  attr_reader :primes
end
