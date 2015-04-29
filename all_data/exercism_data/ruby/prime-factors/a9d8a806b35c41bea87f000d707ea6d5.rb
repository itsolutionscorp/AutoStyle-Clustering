class PrimeFactors
  def self.for(n)
    acc = []
    n = test_prime(acc, n, 2)
    (3..n).each do |p|
      if n < p then break end
      n = PrimeFactors.test_prime(acc, n, p)
    end
    acc
  end

  def self.test_prime(acc, n, p)
    while n % p == 0
      acc << p
      n = n / p
    end
    n
  end

end
