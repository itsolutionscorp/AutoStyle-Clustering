class Sieve

  def initialize(number)
    @range = (2..number)
  end

  def primes
    non_primes = []
    primes = []
    
    @range.to_a.each do |element|
      non_prime=element
      while non_prime < @range.last
        non_prime += element
        non_primes << non_prime
      end
    end
      
    @range.to_a.each do |element|
      primes << element if !non_primes.include?(element)
    end

    primes

  end

end
