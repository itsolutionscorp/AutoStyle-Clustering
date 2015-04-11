class PrimeFactors
  def self.for(n)
    d = 2
    ds = []
    while n != 1
      if n % d == 0
        ds << d
        n /= d
      else
        d += 1
      end
    end
    ds
  end
end
