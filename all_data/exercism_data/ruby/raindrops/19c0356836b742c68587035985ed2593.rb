require 'prime'
module Raindrops
  extend self

  def convert(n)
    primes = n.prime_division.flatten
    if (primes & [3,5,7]).length > 0
      primes.map do |x|
        case x
        when 3 then 'Pling'
        when 5 then 'Plang'
        when 7 then 'Plong'
        end
      end.join
    else
      n.to_s
    end
  end
end
