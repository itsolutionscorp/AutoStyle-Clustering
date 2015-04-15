class Raindrops
  NR_OF_DROPS_TO_SOUND = {3 => 'Pling',
                          5 => 'Plang',
                          7 => 'Plong'}

  def self.convert(number_of_raindrops)
    result = NR_OF_DROPS_TO_SOUND.map{|nr_of_drops_to_special_sound,sound|
      sound if number_of_raindrops % nr_of_drops_to_special_sound == 0
    }.join
    result.empty? ? number_of_raindrops.to_s : result
  end
end
