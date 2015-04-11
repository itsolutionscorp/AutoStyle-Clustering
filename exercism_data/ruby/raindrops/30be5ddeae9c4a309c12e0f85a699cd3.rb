#
# Raindrops converts a number to raindrop-speak.
# See README.md for further details.
#
module Raindrops
  def self.convert(number)
    raindrop_speak = ''
    raindrop_speak += 'Pling' if (number % 3) == 0
    raindrop_speak += 'Plang' if (number % 5) == 0
    raindrop_speak += 'Plong' if (number % 7) == 0
    raindrop_speak = number.to_s if raindrop_speak == ''
    raindrop_speak
  end
end
