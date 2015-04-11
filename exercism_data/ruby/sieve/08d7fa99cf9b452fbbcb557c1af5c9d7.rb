require 'prime'

class Sieve
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def primes
    (2..num).select{ |n| n.prime?}
  end
end
