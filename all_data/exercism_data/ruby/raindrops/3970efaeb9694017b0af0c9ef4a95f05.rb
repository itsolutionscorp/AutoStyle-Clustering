require 'prime'
module Raindrops
  extend self

  def convert(n)
    c = { 3 => 'i' , 5 => 'a' , 7 => 'o'}
    primes = n.prime_division.flatten
    return n.to_s unless (primes & c.keys).length > 0
    primes.select { |x| c.keys.include?(x) }
          .map    { |x| "Pl#{c[x]}ng" }
          .join
  end

end
