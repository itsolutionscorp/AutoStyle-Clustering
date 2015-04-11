require 'prime'

class Raindrops
  def self.convert(input)
    factors = Prime.prime_division(input).flatten.uniq.sort
    output = ''
    output << 'Pling' if factors.include?(3)
    output << 'Plang' if factors.include?(5)
    output << 'Plong' if factors.include?(7)
    output.empty? ? input.to_s : output
  end
end
