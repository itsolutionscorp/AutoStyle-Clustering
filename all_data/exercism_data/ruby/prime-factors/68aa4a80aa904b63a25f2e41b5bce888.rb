class PrimeFactors
  def self.for(n)
    res = []
    i = 2
    limit = Math.sqrt(n)
    while i <= limit
      if n % i == 0
        res << i
        n /= i
        limit = Math.sqrt(n)
      else
        i += 1
      end
    end
    res << n if n > 1
    res
  end
end
