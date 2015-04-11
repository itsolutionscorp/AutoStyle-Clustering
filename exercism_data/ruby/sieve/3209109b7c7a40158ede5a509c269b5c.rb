require 'prime'

class Sieve
  attr_reader :primes

  def initialize(max)
    @primes = Prime::EratosthenesGenerator.new.take_while { |n| n <= max }
  end
end
