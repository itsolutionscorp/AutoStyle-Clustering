class Raindrops
  FACTORS_AND_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(x)
    factors = FACTORS_AND_SOUNDS.keys

    return x.to_s if factors.none? { |y| entire_division?(x, y) }

    sounds = factors.map do |f|
      FACTORS_AND_SOUNDS[f] if entire_division?(x, f)
    end

    sounds.join
  end

  private

  def self.entire_division?(a, b)
    a % b == 0
  end
end
