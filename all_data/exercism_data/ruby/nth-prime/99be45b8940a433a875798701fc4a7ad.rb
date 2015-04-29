class Prime
  class << self
    def nth(number)
      raise ArgumentError if number <= 0

      primes[number - 1] || (
        current = primes.last

        loop do
          current += 2
          primes << current if is_prime?(current)
          return primes.last if primes.length == number
        end
      )
    end

    def primes
      @primes ||= [2, 3]
    end

    def is_prime?(number)
      limit = Math.sqrt(number)

      primes.take_while { |n| n <= limit }.none? do |n|
        number % n == 0
      end
    end
  end
end
