require 'prime'

class PrimeFactors
  attr_accessor :factors, :generator, :prime, :int

  def self.for(int)
    new(int).search_for_factors
  end

  def initialize(int)
    @int = int
    @factors = []
    @generator = Prime::EratosthenesGenerator.new
    @prime = generator.succ
  end

  def search_for_factors
    until int == 1
      factor? ? add_factor : increment_prime
    end
    factors
  end

  def factor?
    int % prime == 0
  end

  def add_factor
    factors << prime 
    self.int /= prime
  end

  def increment_prime
    self.prime = generator.succ
  end
end
