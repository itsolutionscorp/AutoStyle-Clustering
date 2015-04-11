class Raindrops
  SOUND_MAPPINGS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    sounds = sounds_for(number)
    sounds.empty? ? number.to_s : sounds.join
  end

  def self.sounds_for(number)
    SOUND_MAPPINGS.map { |prime, sound| sound if number % prime == 0 }.compact
  end
end
