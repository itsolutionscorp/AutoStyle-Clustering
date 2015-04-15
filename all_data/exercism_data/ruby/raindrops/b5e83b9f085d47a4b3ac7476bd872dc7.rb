require 'prime'

class Raindrops
  DROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def self.convert(number)
    sound = Prime.prime_division(number).flatten.map { |factor|
      DROPS[factor]
    }.join
    sound = number.to_s if sound == ''
    sound
  end
end
