module Raindrops

  SOUNDS = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']]

  def self.convert candidate
    microphone = ->(prime, sound) { (candidate % prime == 0) ? sound : '' }
    
    sound = SOUNDS.map(&microphone).reduce(:+)
    sound.empty? ? candidate.to_s : sound
  end
end
