class Raindrops
  SOUNDS_MAPPING = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
  def self.convert(number)
    return number.to_s if number == 1
    sounds = SOUNDS_MAPPING.select { |factor, sound| sound if (number % factor) == 0 }.values
    sounds.empty? ? number.to_s : sounds.join('')
  end
end
