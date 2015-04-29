require 'prime'

class Raindrops

  DROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    rain_drops = prime_factors(num).map {|factor| DROPS[factor]}.join
    if rain_drops.empty?
      num.to_s
    else
      rain_drops
    end
  end

  def self.prime_factors(num)
    Prime.prime_division(num).flatten.sort.uniq.drop(1)
  end

end
