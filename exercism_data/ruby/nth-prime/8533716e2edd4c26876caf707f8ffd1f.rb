class Prime

  def self.nth(number)
    primes = []
    cont = 2
    while(primes.length < number)
      if Prime.is_prime?(cont)
        primes << cont
      end
      cont += 1
    end
    primes.last
  end

  def self.is_prime?(number)
    for i in 2...number
      if number%i == 0
        return false
      end
    end
    return true
  end

end
