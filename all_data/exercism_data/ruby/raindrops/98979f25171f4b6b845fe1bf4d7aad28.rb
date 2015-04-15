require 'prime'

class Raindrops
  DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(num)
    prime_factors = num.prime_division.sort.map { |arr| arr[0] }

    raindrops = prime_factors.map do |factor|
      DROPS[factor]
    end.join

    raindrops.empty? ? num.to_s : raindrops
  end
end
