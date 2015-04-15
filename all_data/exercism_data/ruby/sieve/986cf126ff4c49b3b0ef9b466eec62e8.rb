class Sieve
  attr_reader :primes
  def initialize (limit)
    
    @primes, possibles, multiples = [], [], []

    2.upto(limit){|i| possibles[i-2] = i}
    
    possibles.each_with_index{|x, i| 
      until x > limit || (x * (i + 2)) > limit do
        multiples << (x * (i + 2)) 
        i += 1
      end
    }
    @primes = possibles - multiples
  end

end
