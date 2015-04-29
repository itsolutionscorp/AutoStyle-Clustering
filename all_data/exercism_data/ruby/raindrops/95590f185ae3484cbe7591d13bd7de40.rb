require 'prime'
module Raindrops
  extend self

  def convert(n)
    primes = n.prime_division.flatten
    return n.to_s unless no_simple_primes?(primes)
    primes.select { |x| small_prime?(x) }
          .map    { |x| "Pl#{conversions[x]}ng" }
          .join
  end

  private

  def no_simple_primes?(primes)
    (primes & conversions.keys).length > 0
  end

  def small_prime?(n)
    conversions.keys.include?(n)
  end

  def conversions
    { 3 => 'i' , 5 => 'a' , 7 => 'o'}
  end
end
