class Sieve
  attr_reader :range

  def initialize(maximum)
    @range = (2..maximum).to_a
  end

  def primes
   primes = []
    while range.any?
     primes << (contender = range.shift)
     range.reject! { |input| input % contender == 0 }
    end
   primes
  end
end
