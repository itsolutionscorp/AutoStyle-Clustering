module Prime
  def self.odds
    # 3, 5, 7, etc.
    (2..Float::INFINITY).lazy.map{ |i| 2 * i - 1 }
  end

  def self.nth(n)
    # ensure the argument is a positive integer
    raise ArgumentError unless n.is_a?(Integer) && n > 0

    # special casing this makes the code below cleaner
    return 2 if n == 1

    # iterating on just the odds lets us skip half the numbers we'd have to
    # test otherwise. that means our streams of "multiples of known primes"
    # starts empty.
    primes = []
    odds.each do |candidate|
      # if the candidate matches the head of one (or more) of the streams of
      # "multiples of known primes", it's not prime
      factors = primes.select{ |stream| stream.peek == candidate }
      if factors.any?
        # candidate is not prime. advance the matching streams of prime
        # multiples
        factors.each{ |stream| stream.next }
      else
        # candidate is prime. add its stream of multiples to the list. we only
        # consider odd multiples because even multiples are even and would
        # never match.
        primes << odds.map{ |i| i * candidate }

        # stop at n - 1 instead of n because we special cased 2
        return candidate if primes.size == n - 1
      end
    end
  end
end
