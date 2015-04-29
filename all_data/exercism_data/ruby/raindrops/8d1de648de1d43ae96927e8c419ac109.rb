class Integer
  def prime_factors
    n  = self
    n *= -1 if n < 0

    factors = []

    while n > 1
      factor = (2..n).find { |i| n % i == 0 }
      factors.push(factor)
      n /= factor
    end

    factors
  end
end

class Raindrops
  RAINDROP_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(n)
    melody = n.prime_factors & RAINDROP_SOUNDS.keys

    if melody.empty?
      n.to_s
    else
      melody.map { |drop| RAINDROP_SOUNDS[drop] }.join
    end
  end
end
