class Raindrops

  # Order is important
  RAINDROP_SPEAK = {
    1 => '',
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    output = RAINDROP_SPEAK.reduce('') { |memo, (key, _)|
      memo + RAINDROP_SPEAK[number.gcd(key)]
    }

    output.empty? ? number.to_s : output
  end

end
