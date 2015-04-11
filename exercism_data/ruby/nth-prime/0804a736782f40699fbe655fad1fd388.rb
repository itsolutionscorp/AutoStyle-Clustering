module Prime

  def self.nth(order)
    raise ArgumentError if order == 0

    primes  = [2]
    current = 2

    until primes.size == order do
      current += 1
      primes << current unless primes.any? { |prime| current % prime == 0 }
    end

    primes.last
  end

end
