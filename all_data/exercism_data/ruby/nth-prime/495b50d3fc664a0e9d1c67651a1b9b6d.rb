class Prime
  FIRST_TEN ||= [2,3,5,7,11,13,17,19,23,29]
  # Return the nth prime.
  def self.nth (n)
    raise ArgumentError if n < 1

    return FIRST_TEN[n-1] if n < 11

    c = 10; p = FIRST_TEN[-1]
    until (c == n)
      p = Prime.next_after p
      c += 1
    end

    p
  end

  # Simple modulus test of primacy
  def self.number? (n)
    return false if n < 2
    (2..(Math.sqrt n)).each do |i|
      return false if n % i == 0
    end
    return true
  end

  # Naive search for primes.
  def self.next_after (n)
    raise ArgumentError unless Prime.number? n
    return FIRST_TEN[FIRST_TEN.index(n)+1] if (n < FIRST_TEN[-1])
    loop do
      n += 2 # Increment by twos because all primes are odd
      break if Prime.number? n
    end
    n
  end
end
