class Raindrops
  def self.convert(number)
    sound_for(number)
  end

  def self.sound_for(number)
    sound = String.new
    sound << 'Pling' if number % 3 == 0
    sound << 'Plang' if number % 5 == 0
    sound << 'Plong' if number % 7 == 0

    if sound.empty?
      sound = number.to_s
    end
    sound
  end
end
