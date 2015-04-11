require 'prime'

class PrimeFactors
  def self.for(number)
    new(number).prime_factor
  end

  def initialize(number)
    @number = number
    @primes = [2]
    @current_prime = @primes.first
    @factors = []
  end

  def prime_factor
    while number > 1
      while divisible_by?(current_prime)
        @number /= current_prime
        factors << current_prime
      end
      generate_next_prime
    end
    factors
  end

  def divisible_by?(prime)
    number % prime == 0
  end

  def generate_next_prime
    @current_prime = prime_generator.next
  end

  def prime_generator
    @prime_generator ||= Prime::EratosthenesGenerator.new
  end

  private
  attr_accessor :number, :current_prime, :primes, :factors
end
