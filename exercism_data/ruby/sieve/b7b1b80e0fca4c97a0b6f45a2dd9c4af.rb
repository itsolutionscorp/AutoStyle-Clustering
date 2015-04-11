class Sieve
  attr_reader :limit
  attr_accessor :primes

  def initialize(limit)
    @limit      = limit
    @primes     = calculate_primes
  end

  def calculate_primes
    numbers = 2.upto(limit).to_a

    numbers.each do |p|
      numbers.delete_if { |n| n != p && n % p == 0 }
    end

    numbers
  end
end
