module Raindrops
  class << self
    DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

    def convert(n)
      factors = DROPS.keys.select { |factor| n % factor == 0 }
      String(factors.empty? ? n : DROPS.values_at(*factors).join)
    end
  end
end
