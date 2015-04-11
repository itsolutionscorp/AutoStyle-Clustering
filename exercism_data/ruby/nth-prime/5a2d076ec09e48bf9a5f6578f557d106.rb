class Prime
  @@primes = [2, 3, 5, 7, 11]

  class << self

    def nth(n)
      raise ArgumentError.new('n must be non-zero positive integer') if n < 1

      generate_primes_upto(upperbound(n)) if @@primes.length < n

      return @@primes[n - 1]
    end

    private

    # returns the upperbound for nth prime
    # see http://stackoverflow.com/questions/1042717/is-there-a-way-to-find-the-approximate-value-of-the-nth-prime
    def upperbound(n)
      (n * Math.log(n) + n * Math.log(Math.log n)).ceil
    end

    # uses the Sieve of Erastosthenes to generate primes up to and including `limit`
    def generate_primes_upto(limit)
      # only generate primes up to last known ^ 2
      # this ensures that @@primes contains at least one divisor
      # for every non-prime from 0 to limit
      while @@primes.last ** 2 < limit
        generate_primes_upto(@@primes.last ** 2)
      end

      # future optimisation: ignore even numbers when generating candidates
      candidates = (0..limit).to_a

      # set all candidates that are multiples of known primes to zero
      @@primes.each do |p|
        (p..limit).step(p).each { |multiple| candidates[multiple] = 0 }
      end

      # remaining candidates are primes
      @@primes += candidates.select { |p| p > @@primes.last }
    end
  end
end
