require 'prime'


class PrimeFactors

  def self.for(num)
    primes = []
    number = num
    while number > 1
      Prime.each(number) do |prime|
        if number % prime == 0
          primes << prime
          number = number / prime
        end
      end
    end
    primes
  end
end
