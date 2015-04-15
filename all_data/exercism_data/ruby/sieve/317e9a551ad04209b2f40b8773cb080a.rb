class Sieve
  def initialize(n)
    @primes = [nil, nil, *2..n]
    @p = 2                      # Pointer to the last prime.
  end

  def primes
    @primes.tap do |primes|
      while @p
        @p.abs2.step(by: @p, to: primes.length) { |n| primes[n] = nil }
        @p = primes.slice(@p.succ..-1).find(&:itself)
      end
    end.compact
  end
end
