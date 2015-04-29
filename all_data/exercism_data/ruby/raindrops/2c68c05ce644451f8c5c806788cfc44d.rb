class Raindrops
  FACTORS_AND_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(x)
    divides_x = -> (y) { x % y == 0 }

    factors = FACTORS_AND_SOUNDS.keys

    return x.to_s if factors.none?(&divides_x)

    factors.
      keep_if(&divides_x).
      map { |f| FACTORS_AND_SOUNDS[f] }.
      join
  end
end
