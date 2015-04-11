class Sieve
  attr_accessor :unmarked_numbers, :primes_ary, :no_primes_ary, :max

  def initialize(i)
    self.max = i
    self.no_primes_ary = []
    self.primes_ary = []
  end

  def primes
    unmarked_numbers = (2..max).to_a
    while unmarked_numbers.any?
      current_prime = unmarked_numbers.shift
      self.primes_ary << current_prime
      (max/current_prime + 1).times do |j|
        self.no_primes_ary << unmarked_numbers.delete(j * current_prime)
      end
    end
    primes_ary.sort
  end
end
