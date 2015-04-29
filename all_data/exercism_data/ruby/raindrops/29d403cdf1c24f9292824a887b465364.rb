class Raindrops
  class << self
    def convert(drops)
      factors = find_factors(drops)

      if factors.length > 0
        join_sounds(factors)
      else
        drops.to_s
      end
    end

    def find_factors(drops)
      sounds.keys.select do |sound|
        drops % sound == 0
      end
    end

    def join_sounds(factors)
      factors.collect do |factor|
        sounds[factor]
      end.join
    end

    def sounds
      {
        3 => "Pling",
        5 => "Plang",
        7 => "Plong",
      }
    end

  end
end
