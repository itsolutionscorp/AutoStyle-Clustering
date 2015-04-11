module Raindrops
  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    sounds = SOUNDS.keys.each_with_object('') do |sound_key, translation|
      translation << SOUNDS[sound_key] if number.modulo(sound_key).zero?
    end

    sounds.empty? ? number.to_s : sounds
  end
end
