class Raindrops
  SOUND_MAPPINGS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    sounds = SOUND_MAPPINGS.select { |prime, _| number % prime == 0 }.values
    sounds.empty? ? number.to_s : sounds.join
  end
end
