module Prime
  def self.nth(n)
    # ensure the argument is a positive integer
    raise ArgumentError unless n.is_a?(Integer) && n > 0

    # special casing this makes the code below cleaner
    return 2 if n == 1

    # just iterating 3, 5, 7, etc. lets us skip half the candidates
    candidates = (2..Float::INFINITY).lazy.map{ |i| i * 2 - 1 }

    # since we're already only doing odd numbers, don't need 2 in the list of
    # prime factors to test, so this starts empty
    primes = []
    multiples = []

    candidates.each do |candidate|
      # if the candidate is one (or more) of the "next multiple of seen primes"
      # list, it's not prime. but before skipping it, advance the "next
      # multiple of primes" entries it matches (by 2 * prime, not just prime,
      # because we're only looking at the odds)
      if multiples.include?(candidate)
        multiples.size.times do |i|
          multiples[i] += 2 * primes[i] if multiples[i] == candidate
        end
      else
        # x is prime. the next number that will have x as a factor is 3x (since
        # 2x is even and we're already skipping even numbers)
        primes << candidate
        multiples << 3 * candidate

        # we want prime n - 1 because we skipped 2
        return candidate if primes.size == n - 1
      end
    end
  end
end
