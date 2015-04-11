# version 1

require 'prime'

module Raindrops
  
  def self.convert(n)
    a = n.prime_division.flatten.keep_if { |i| i == 3 || i == 5 || i == 7 }
    if a.empty?
      return n.to_s
    else
      a.join.squeeze.sub('3', 'Pling').sub('5', 'Plang').sub('7', 'Plong')
    end
  end
  
end

# Converts number to string, contents depend on number's prime factors.
# If the number contains 3 as a prime factor, output 'Pling'.
# If the number contains 5 as a prime factor, output 'Plang'.
# If the number contains 7 as a prime factor, output 'Plong'.
# If the number does not contain 3, 5, or 7 as a prime factor,
# just pass the number's digits straight through.
