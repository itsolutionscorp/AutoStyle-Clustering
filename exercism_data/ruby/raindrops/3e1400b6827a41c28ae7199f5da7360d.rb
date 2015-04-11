class Raindrops
  FACTORS_AND_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(x)
    divides_x = -> (y) { x % y == 0 }

    return x.to_s if FACTORS_AND_SOUNDS.keys.none?(&divides_x)

    FACTORS_AND_SOUNDS.
      select { |k,v| divides_x[k] }.
      values.
      join
  end
end
