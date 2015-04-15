class Prime
  class << self

    def nth(nth_prime_number)
      check_if_valid_number(nth_prime_number)
      return 2 if nth_prime_number == 1

      number_of_primes = 1 # Initiate at 1 since 2 is the only even prime number
      test_number = 3

      until number_of_primes == nth_prime_number
        if prime?(test_number)
          number_of_primes += 1
        end

        test_number += 2
      end

      test_number -= 2
    end

    def prime?(number)
      divisor = number - 1

      until divisor == 2
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
