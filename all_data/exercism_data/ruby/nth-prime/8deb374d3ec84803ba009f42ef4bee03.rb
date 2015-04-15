class Prime

  @@sieve = Array.new(500001,0)
  num, limit = 1, 500000
  @@sieve[0], @@sieve[1] = nil, nil

  (2..limit).each do |x, y = @@sieve[x]|
    if(y==0)
      n = x*x
      while(n < limit) do
        @@sieve[n] = nil
        n = n + x
      end
      @@sieve[x] = x
    end
  end

  @@sieve.compact!

  def self.nth(idx)
    raise ArgumentError, 'Argument is smaller than 1' unless idx > 0
    @@sieve[idx - 1]
  end

end
