class Prime
  def self.nth(n)
    validate_input(n)
    find_primes_to_nth(n)
    primes[n - 1]
  end

  class << self
    private

    def validate_input(n)
      fail(ArgumentError, 'n must be greater than zero') unless n > 0
    end

    def find_primes_to_nth(n)
      while primes.size < n
        primes << next_candidate if prime?(next_candidate)
        @next_candidate += 2
      end
    end

    def prime?(number)
      upper_bound = Math.sqrt(number).floor
      is_prime = true
      primes.each do |prime|
        break if prime > upper_bound
        break unless is_prime &&= !multiple?(number, prime)
      end
      is_prime
    end

    def primes
      @primes ||= [2]
    end

    def next_candidate
      @next_candidate ||= 3
    end

    def multiple?(n, divisor)
      n % divisor == 0
    end
  end
end
