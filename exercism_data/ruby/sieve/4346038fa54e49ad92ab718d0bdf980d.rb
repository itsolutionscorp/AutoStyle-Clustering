class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    primes = []

    range = [true] * (@limit - 1)

    range.each_with_index do |prime, i|
      k = i + 2
      if prime
        primes << k
        2.upto(@limit / k) { |x| range[x * k - 2] = false }
      end
    end

    primes
  end
end
