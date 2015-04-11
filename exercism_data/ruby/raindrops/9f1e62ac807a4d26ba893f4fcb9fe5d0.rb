class SoundCollection
  def initialize(sounds)
    @sounds = sounds
  end

  def sound(*args)
    to_return = @sounds.map { |s| s.sound(*args) }.join
    to_return.length > 0 ? to_return : nil
  end
end

class RaindropSound
  def initialize(prime_factor, noise)
    @prime_factor, @noise = prime_factor, noise
  end

  def sound(drops)
    @noise if (drops % @prime_factor).zero?
  end
end

class Raindrops
  def self.convert(drops)
    sound_collection = SoundCollection.new([
      RaindropSound.new(3, "Pling"),
      RaindropSound.new(5, "Plang"),
      RaindropSound.new(7, "Plong")
    ])

    sound_collection.sound(drops) || drops.to_s
  end
end
