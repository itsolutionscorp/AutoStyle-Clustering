class Raindrops
  def self.convert(thing)
    raindrops = ''

    sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

    sounds.inject(raindrops)  do | raindrops, (drop, sound)|
      if thing % drop == 0
        raindrops << sound
      end
      raindrops
    end

    if raindrops == ''
      raindrops << thing.to_s
    end

    raindrops
  end
end
