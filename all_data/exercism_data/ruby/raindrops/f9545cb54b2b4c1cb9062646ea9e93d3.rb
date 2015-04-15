require 'prime'
require 'set'

class Raindrops

  def self.convert(nbr)
    plout = plonger(prime_factors(nbr))
    return nbr.to_s if plout.empty?
    plout
  end

  def self.prime_factors(nbr)
    Prime.prime_division(nbr).map do |(fct, pwr)|
      fct
    end.to_set
  end

  def self.plonger(factors)
    '' << pling(factors) << plang(factors) << plong(factors)
  end

  def self.pling(factors)
    return 'Pling' if factors.include?(3)
    ''
  end

  def self.plang(factors)
    return 'Plang' if factors.include?(5)
    ''
  end

  def self.plong(factors)
    return 'Plong' if factors.include?(7)
    ''
  end

end
