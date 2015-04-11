class Sieve

  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def primes
    sieve = (2..@upper_bound).to_a
    sieve.each do |prime|
      sieve.select {|num| num > prime }.each do |candidate|
        sieve.delete(candidate) if candidate % prime == 0
      end
    end
  end
end
