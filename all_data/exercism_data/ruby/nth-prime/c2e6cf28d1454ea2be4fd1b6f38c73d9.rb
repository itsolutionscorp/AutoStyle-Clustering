class Prime
  def self.nth(num)
    throw ArgumentError if num < 1

    primes = [2, 3]

    return primes[num - 1] if num == 1 || num == 2

    test = primes.last

    while primes.length < num
      test += 2
      is_prime = true

      primes.each do |prime|
        if test % prime == 0
          is_prime = false
          break
        end
      end

      primes.push(test) if is_prime
    end

    primes.last
  end
end
