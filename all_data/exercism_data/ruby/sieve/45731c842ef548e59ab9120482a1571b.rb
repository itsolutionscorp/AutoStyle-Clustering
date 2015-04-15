require 'pry'

class Sieve
  def initialize(end_range)
    @end_range = end_range
  end

  def primes
    possible_primes = (2..@end_range).to_a

    possible_primes.each do |num|
      possible_primes.delete_if { |x| x % num == 0 unless x == num }
    end
    possible_primes
  end
end
