class Sieve
  def initialize(limit)
    @limit = limit
    @all_numbers = (2..limit).to_a
  end

  def primes
    @all_numbers.each { |prime| remove_non_prime(prime) }
    @all_numbers
  end

  def remove_non_prime(prime)
    @all_numbers.delete_if { |number| number % prime == 0 && number != prime }
  end

end
