class Sieve
  def initialize(limit)
    @limit = limit
    @primes = (2..@limit).to_a
  end

  def primes
    @primes.each { |prime| delete_all_multiples_of(prime) }
  end

  private

  def delete_all_multiples_of(prime)
    (prime+1..@limit).to_a.each do |test_number|
      @primes.delete(test_number) if test_number % prime == 0
    end
  end
end
