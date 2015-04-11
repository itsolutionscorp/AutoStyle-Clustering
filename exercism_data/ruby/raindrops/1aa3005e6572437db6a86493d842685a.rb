class Raindrops
  def self.convert(n)
    drops = []

    if n % 3 == 0
      drops << 'Pling'
    end

    if n % 5 == 0
      drops << 'Plang'
    end

    if n % 7 == 0
      drops << 'Plong'
    end

    if n % 3 != 0 && n % 5 != 0 && n % 7 != 0
      drops << n.to_s
    end

    drops.inject(:+)
  end
end
