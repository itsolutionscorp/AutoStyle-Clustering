# def factors(n)
#   (1..n).select{ |x| n%x == 0 }
# end

# def is_prime?(n)
#   factors(n).length <= 2
# end

# def prime_factors(n)
#   factors = factors(n)[1..-2]
#   factors.select{ |x| is_prime?(x) }
# end
require 'prime'

def prime_factorization_unique(n)
  factorization = Prime.prime_division(n)
  factorization.map { |e| e[0] }
end

module Raindrops
  def self.convert(n)
    factors = prime_factorization_unique(n)
    str = ''
    str << 'Pling' if factors.include?(3)
    str << 'Plang' if factors.include?(5)
    str << 'Plong' if factors.include?(7)
    return n.to_s if str == ''
    str
  end
end
