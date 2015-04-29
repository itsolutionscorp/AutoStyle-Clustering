class Sieve
  attr_accessor :primes

  def initialize(n)
     @primes = get_primes(n)
  end

  def get_primes(n)
    primes = (2..n).to_a
    primes.each do |num|
      primes.delete_if { |i| i % num == 0 and i != num }
    end
  end
end
