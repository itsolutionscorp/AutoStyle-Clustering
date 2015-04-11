class Raindrops
  class << self

    def convert(num)
      sounds_for(num) || num.to_s
    end

    private

    SOUNDS = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong',
    }

    def sounds_for(num)
      sounds = ""
      SOUNDS.each do |divisor, sound|
        sounds += sound if (num % divisor).zero?
      end
      sounds.empty? ? nil : sounds
    end
  end
end
