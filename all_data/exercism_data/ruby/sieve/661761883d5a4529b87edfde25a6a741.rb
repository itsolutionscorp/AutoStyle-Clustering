class Sieve

  attr_reader :limit

  def initialize limit
    @limit = limit
  end

  def primes
    sieve (2..limit).to_a
  end

private

  def sieve numbers
    prime, sieved_numbers = perform_sieve_on numbers

    prime ? [ prime, *sieve( sieved_numbers ) ] : []
  end

  def perform_sieve_on numbers
    prime          = numbers.shift
    sieved_numbers = remove_multiples_of prime, numbers

    [ prime, sieved_numbers ]
  end

  def remove_multiples_of prime, numbers
    numbers.reject { |number| number % prime == 0 }
  end

end
