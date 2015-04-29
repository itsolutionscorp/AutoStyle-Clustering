require 'prime'

class Raindrops
  @map = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(n)
    output = []
    factors = Prime.prime_division(n)
    factors.each do |f|
      output << @map.fetch(f[0], nil)
    end
    return n.to_s if output.join.empty?
    output.join
  end

end
