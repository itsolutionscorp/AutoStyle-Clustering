require 'pry'

class Sieve
  attr_accessor :primes

  def initialize(max)
    @primes = sieve(max)
  end

  def sieve(max)
    result = (2..max).to_a
    
    index = 0

    loop do
      break if index > (result.size - 1)
      result.delete_if {|e| (e % result[index]).zero? and (e != result[index])}
      index += 1
    end

    result
  end
end
