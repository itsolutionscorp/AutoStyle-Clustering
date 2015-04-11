class Prime
  class << self

    def nth(nth_prime_number)
      check_if_valid_number(nth_prime_number)
      primes = []
      test_number = 2

      until primes.size == nth_prime_number # Can I make this an inject in some way?
        if test_number.even? && test_number > 2
          test_number += 1
          next
        end

        if prime?(test_number)
          primes << test_number
        end

        test_number += 1
      end

      primes.last
    end

    def prime?(number)
      divisor = number - 1

      until divisor < 2
        if divisible?(number, divisor)
          return false
        end
        divisor -= 1
      end
      return true
    end

    def check_if_valid_number(number)
      raise ArgumentError if number < 1
    end

    def divisible?(dividend, divisor)
      (dividend % divisor).zero?
    end
  end
end
