class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)

    final_sound = SOUNDS.keys.inject(nil) do |sound, n| 
      number % n == 0 ? sound.to_s + SOUNDS[n] : sound
    end

    final_sound || number.to_s
  end
end
