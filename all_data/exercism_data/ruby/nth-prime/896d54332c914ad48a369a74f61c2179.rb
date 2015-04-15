require 'pry'

class Prime
  def self.nth(n)
    primes = (2..n**2).to_a
    
    index = 0

    loop do
      break if primes[index] > n
      primes.delete_if {|e| (e % primes[index]).zero? and (e != primes[index])}
      index += 1
    end

    primes[n - 1]
  end
end

puts Prime.nth(1000)
