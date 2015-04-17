class Raindrops
  NR_OF_DROPS_TO_SOUND = {3 => 'Pling',
                          5 => 'Plang',
                          7 => 'Plong'}

  def self.convert(number_of_raindrops)
    result = ''
    NR_OF_DROPS_TO_SOUND.each{|nr_of_drops_to_special_sound,sound|
          if number_of_raindrops % nr_of_drops_to_special_sound == 0
            result += sound
          end
    }
    result != '' ? result : number_of_raindrops.to_s
  end
end