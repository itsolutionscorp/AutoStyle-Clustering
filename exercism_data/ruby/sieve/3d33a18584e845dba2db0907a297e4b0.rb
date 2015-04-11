class Sieve
  attr_reader :limit
  attr_accessor :primes

  def initialize(limit)
    @limit      = limit
    @primes     = calculate_primes
  end

  def calculate_primes
    primes     = 2.upto(limit).to_a

    primes.each do |p|
      primes.delete_if { |n| n != p && n % p == 0 }
    end

    primes
  end
end
