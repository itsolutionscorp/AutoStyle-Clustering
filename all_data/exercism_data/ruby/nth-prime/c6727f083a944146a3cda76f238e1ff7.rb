class Fixnum
  def divisible_by?(divisor)
    self % divisor == 0
  end
end

class Prime
  def self.nth(target_index)
    raise ArgumentError if target_index < 1
    primes = [2, 3]
    while primes.length < target_index do
      primes << next_prime(primes)
    end
    primes[target_index - 1]
  end

  def self.next_prime(primes)
    prime_candidate = primes.last + 2
    while true do
      primes.each do |prime|
        if prime_candidate.divisible_by?(prime)
          prime_candidate += 2
          break
        elsif prime > Math.sqrt(prime_candidate)
          return prime_candidate
        end
      end
    end
  end
end
