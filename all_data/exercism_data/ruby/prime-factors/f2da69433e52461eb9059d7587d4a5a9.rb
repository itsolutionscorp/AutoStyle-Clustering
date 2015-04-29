class Fixnum
  def divisible_by?(num)
    self % num == 0
  end
end

module PrimeFactors
  def self.for(num)
    factors = []

    return [] if num < 2

    (1..Float::INFINITY).each do |prime_index|
      prime = Prime[prime_index]
      while num.divisible_by?(prime)
        factors.push(prime)
        num /= prime
      end
      break if num == 1
    end

    factors
  end
end

module Prime
  @@primes = [2, 3]

  class << self
    def primes
      @@primes
    end

    def [](num)
      nth(num)
    end

    def nth(num)
      throw ArgumentError if num < 1
      return primes[num - 1] unless primes[num - 1].nil?

      test = primes.last

      while primes.length < num
        test += 2
        is_prime = true

        primes.each do |prime|
          if test.divisible_by?(prime)
            is_prime = false
            break
          end
        end

        primes.push(test) if is_prime
      end

      primes.last
    end
  end
end
