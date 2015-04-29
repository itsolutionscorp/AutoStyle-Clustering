require 'prime'

class Raindrops
  def self.convert(n)
    pf = prime_factorization(n)
    check_sound(compose_sound(pf), n)
  end

  def self.prime_factorization(n)
    Prime.prime_division(n).flat_map { |factor, power| [factor] * power }
  end

  def self.compose_sound(pf)
    sound = ''
    pf.each do |p|
      sound = sound + prime_to_sound(p)
    end
    sound
  end

  def self.prime_to_sound(p)
    case p
    when 3
      'Pling'
    when 5
      'Plang'
    when 7
      'Plong'
    else
      ''
    end
  end

  def self.check_sound(sound_string, n)
    if sound_string.include? "Pling" or sound_string.include? "Plang" or sound_string.include? "Plong"
      reduce(sound_string)
    else
      n.to_s
    end
  end

  def self.reduce(sound_string)
    sound_string.sub! "PlingPling", "Pling"
    sound_string.sub! "PlangPlang", "Plang"
    sound_string.sub! "PlongPlong", "Plong"
    sound_string
  end
end


if __FILE__ == $0
  puts Raindrops.prime_factorization(9)
end
