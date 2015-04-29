class Raindrops

  def convert(n)
    divisors = divisors_with_sound(n)

    if divisors.empty?
      n.to_s
    else
      sounds_of(divisors)
    end
  end

private

  def divisors_with_sound(n)
    DIVISORS_SOUNDS.select do |divisor, sound|
      n.modulo(divisor).zero?
    end
  end

  def sounds_of(divisors)
    divisors.map(&:last).join
  end

  DIVISORS_SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
end
