class Raindrops
  def self.convert(input)
    output = to_raindrops(factorize(input))
    return input.to_s if output == ""
    output
  end

  private
  PRIMES = [2,3,5,7,11,13,17]
  def self.factorize(input, factors = [])
    return factors if input == 1
    PRIMES.each do |prime|
      return factorize(input/prime, factors << prime) if input % prime == 0
    end
    factors
  end

  DROPS = [[3,"Pling"],[5,"Plang"],[7,"Plong"]]

  def self.to_raindrops(factors)
    DROPS.inject(""){|out, tup| factors.index(tup[0]) ? (out + tup[1]) : out }
  end
end
