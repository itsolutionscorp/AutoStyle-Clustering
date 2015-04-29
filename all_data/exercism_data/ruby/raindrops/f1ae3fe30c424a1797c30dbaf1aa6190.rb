require 'prime'
module Raindrops
  extend self

  def convert(n)
    return n.to_s unless no_simple_primes?(primes(n))
    primes(n).select(&method(:small_prime?)).map(&method(:pl_ng)).join
  end

  private

  def primes(n)
    n.prime_division.flatten
  end

  def no_simple_primes?(primes)
    (primes & conversions.keys).length > 0
  end

  def small_prime?(n)
    conversions.keys.include?(n)
  end

  def pl_ng(n)
    "Pl#{conversions[n]}ng"
  end

  def conversions
    { 3 => 'i' , 5 => 'a' , 7 => 'o'}
  end
end
