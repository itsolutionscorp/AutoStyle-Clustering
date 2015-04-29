class Prime
  def self.nth(n)
    raise ArgumentError if n < 1
    @primes = []
    current = 2

    while @primes.length < n
      if is_prime?(current) then
        @primes << current 
      else
        current = current + 1
      end
    end

    current
  end

  def self.is_prime?(n)
    not @primes.any? { |p| is_factor?(n, p) }
  end

  def self.is_factor?(n, f)
    n % f == 0
  end
end
