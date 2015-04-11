class Prime

  def self.nth(n)
    raise ArgumentError if n < 1
    prime_numbers(n)[-1]
  end

  def self.prime_numbers(no_of_primes)
    return [2] if no_of_primes == 1

    prime_numbers = []

    number = 1

    while prime_numbers.count < no_of_primes
      sq_rt = Math.sqrt(number)
      divisor = 2

      (divisor..sq_rt).each do
        if (number % divisor) != 0 then divisor +=1 else break end
      end

      prime_numbers << number if divisor > sq_rt

      number += 2
    end

    return prime_numbers

  end
end

# Even though Prime has been included with Ruby since 1.9.3 that's not the
# point of the exercise.

# require 'prime'

# Prime.class_eval do
#   def self.nth(how_many)
#     raise ArgumentError if how_many < 1
#     (Prime.first how_many)[-1]
#   end
# end
