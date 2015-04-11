require 'prime'
module Raindrops
  extend self

  def convert(n)
    primes = n.prime_division.flatten
    return n.to_s unless (primes & [3,5,7]).length > 0
    primes.select { |x| [3,5,7].include?(x) }.map { |x| "Pl#{{3=>'i',5=>'a',7=>'o'}[x]}ng"}.join
  end

end
