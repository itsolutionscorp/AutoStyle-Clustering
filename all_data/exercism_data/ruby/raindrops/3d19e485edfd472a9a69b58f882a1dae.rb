require 'prime'

class Raindrops
  def self.convert(drops)
    factors = drops.prime_division.flat_map { |fac, pow| [fac] * pow }
    str = ''
    str << 'Pling' if factors.include? 3
    str << 'Plang' if factors.include? 5
    str << 'Plong' if factors.include? 7
    str = "#{drops}" if str.empty?
    str
  end
end
