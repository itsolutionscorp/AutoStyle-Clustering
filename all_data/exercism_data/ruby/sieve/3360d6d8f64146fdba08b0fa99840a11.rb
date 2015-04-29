require "set"

class Sieve
  def initialize(max)
    @max = max
    @nonprimes = Set.new
  end

  def primes
    2.upto(@max).each_with_object([]) { |number, primes|
      next if nonprime?(number)
      mark_multiples_as_nonprime(number)
      primes << number
    }
  end

  private

  def nonprime?(number)
    @nonprimes.include?(number)
  end

  def mark_multiples_as_nonprime(number)
    2.upto(@max / number) do |factor|
      mark_as_nonprime(number * factor)
    end
  end

  def mark_as_nonprime(number)
    @nonprimes << number
  end
end
