class Prime

  def self.nth(n)
    raise ArgumentError if n == 0
    primes = []
    x = 1
    while primes.length <= n
      primes << x if prime?(x)
      x += 1
    end
    return primes.last
  end

  private

  def self.prime?(x)
    (2..(x-1)).none? { |d| x % d == 0 }
  end

end
