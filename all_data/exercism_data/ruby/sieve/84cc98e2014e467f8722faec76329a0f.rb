class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    primes = []

    range = [true] * (@limit - 1)

    (2..@limit).each do |k|
      if range[k - 2]
        primes << k
        (k**2..@limit).step(k){ |i| range[i - 2] = false }
      end
    end

    primes
  end
end
