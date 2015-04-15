class Prime
  def self.nth(number)
    raise ArgumentError unless number > 0

    primes = []
    prime_candidate = 2
    primes << prime_candidate

    while true
      break if number == primes.size
      prime_candidate += 1
      is_prime = true

      primes.each do |prime|
        if prime_candidate % prime == 0
          is_prime = false
          break
        end
      end

      primes << prime_candidate if is_prime
    end

    primes[-1]
  end
end
