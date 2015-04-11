require 'prime'

class PrimeFactors

  def self.for number
    new( number ).factors
  end

  attr_reader :number

  def initialize number
    @number = number
  end

  def factors
    primes.reduce [] do |factors, (prime, multiplicity)|
      factors += [ prime ] * multiplicity
    end
  end

private

  def primes
    Prime.prime_division number
  end

end
