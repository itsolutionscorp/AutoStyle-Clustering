require 'prime'
module Raindrops
  extend self

  def convert(n)
    primes = n.prime_division.flatten
    return n.to_s unless (primes & [3,5,7]).length > 0
    primes.map { |x| { 3 => 'Pling',
                       5 => 'Plang',
                       7 => 'Plong', }[x] }.join
  end

end
