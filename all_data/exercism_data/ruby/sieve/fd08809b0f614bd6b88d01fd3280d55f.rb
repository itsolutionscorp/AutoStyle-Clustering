class Sieve

  def initialize(limit)
    @numbers = 2.upto(limit)
    @primes = nil
  end

  def primes
    @primes.nil? ? calculate_primes : @primes
  end

  private

  def calculate_primes
    @primes = @numbers.reduce([]) do |primes, number|
      primes << number unless primes.any? { |prime| number % prime == 0 }
      primes
    end
  end
end
