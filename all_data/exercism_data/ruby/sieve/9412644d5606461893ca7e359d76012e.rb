require 'pry'
class Sieve
  attr_reader :maximum 

  def initialize(maximum)
    @maximum = maximum
  end

  def primes
   numbers = (2..maximum).to_a
   primes = []
    while numbers.any?
     primes << (contender = numbers.shift)
      #binding.pry
     numbers.reject! { |input| input % contender == 0 }
    end
   primes
  end
end
