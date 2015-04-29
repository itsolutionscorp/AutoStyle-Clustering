#!/usr/bin/env ruby

# Exercism 14
# Sieve of Eratosthenes

class Sieve

def initialize(num)
  @range = Array.new
  (0..num-2).each { |i| @range[i] = i+2 }
end

def primes
  index = 0
  while @range[index]**2 <= @range.last
    prime = @range[index]
    @range = @range.select { |x| x == prime || x%prime != 0 }
    index += 1
  end
  @range
end

end
