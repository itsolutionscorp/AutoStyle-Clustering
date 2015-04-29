require 'prime'
require 'pry'

class Raindrops
  class << self

    DROP_SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

    def convert(number)
      drop_sound = drop_sound(number)
      drop_sound.any? ? drop_sound.join : number.to_s
    end

    private

    def drop_sound(number)
      DROP_SOUNDS.map{ |factor, sound| sound if number % factor == 0 }.compact
    end

  end
end
