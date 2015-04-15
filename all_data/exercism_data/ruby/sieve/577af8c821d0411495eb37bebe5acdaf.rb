class Sieve

  attr_reader :numbers

  def initialize limit
    @numbers = ( 2..limit ).to_a
  end

  def primes
    sieve numbers
  end

private

  def sieve numbers
    return [] if numbers.empty?

    current_prime  = numbers.shift
    sieved_numbers = remove_multiples_of( current_prime, numbers )

    [ current_prime, *sieve( sieved_numbers ) ]
  end

  def remove_multiples_of prime, numbers
    numbers.reject { |number| ( number % prime ).zero? }
  end

end
