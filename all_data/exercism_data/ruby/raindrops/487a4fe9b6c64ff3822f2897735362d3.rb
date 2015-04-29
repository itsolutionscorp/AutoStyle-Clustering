#
# Define Integer#divisible_by?
#
module IntegerRefinements
  refine Integer do
    def divisible_by?(n)
      (self % n) == 0
    end
  end
end

#
# Activate Integer.divisible_by?
#
using IntegerRefinements

#
# Raindrops converts a number to raindrop-speak.
# See README.md for further details.
#
module Raindrops
  def self.convert(number)
    raindrop_speak = ''
    raindrop_speak += 'Pling' if number.divisible_by? 3
    raindrop_speak += 'Plang' if number.divisible_by? 5
    raindrop_speak += 'Plong' if number.divisible_by? 7
    raindrop_speak = number.to_s if raindrop_speak == ''
    raindrop_speak
  end
end
