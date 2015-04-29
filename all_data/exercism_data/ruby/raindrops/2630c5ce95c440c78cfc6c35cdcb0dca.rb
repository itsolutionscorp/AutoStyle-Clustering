require 'prime'
module Raindrops
  extend self

  def convert(n)
    primes = n.prime_division.flatten
    if primes.any? { |x| [3,5,7].include?(x) }
      primes.map do |x|
        case x
        when 3 then 'Pling'
        when 5 then 'Plang'
        when 7 then 'Plong'
        else x
        end
      end.select { |x| x.is_a?(String) }.join
    else
      n.to_s
    end
  end
end
