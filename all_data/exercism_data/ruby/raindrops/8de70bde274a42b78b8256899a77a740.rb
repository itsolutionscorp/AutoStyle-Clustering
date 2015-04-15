require 'prime'

class Raindrops
  def self.convert(n)
    unique_factors = Prime.prime_division(n).map { |f, n| f }
    rainspeak = rainspeak_from unique_factors

    return n.to_s if rainspeak == ''
    return rainspeak
  end

  private
  def self.rainspeak_from(factors)
    rainspeak = ''
    rainspeak << 'Pling' unless factors.index(3).nil?
    rainspeak << 'Plang' unless factors.index(5).nil?
    rainspeak << 'Plong' unless factors.index(7).nil?
    rainspeak
  end
end
