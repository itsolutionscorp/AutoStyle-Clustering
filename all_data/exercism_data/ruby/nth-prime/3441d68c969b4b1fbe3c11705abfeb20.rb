class Prime
  def self.nth(n)
    raise ArgumentError, 'n must be greater than zero' unless n > 0
    primes = self.get_list_of_primes(n)
    return primes[n - 1]
  end

  # return a list of the first n primes
  def self.get_list_of_primes(n)
    raise ArgumentError, 'n must be greater than zero' unless n > 0
    primes = [2]
    natural_number = 2

    while primes.length < n
      natural_number += 1
      is_prime = true
      for prime in primes
        if natural_number % prime == 0
          is_prime = false
          break
        end
      end

      if is_prime
        primes[primes.length] = natural_number
      end
    end

    return primes
  end
end
