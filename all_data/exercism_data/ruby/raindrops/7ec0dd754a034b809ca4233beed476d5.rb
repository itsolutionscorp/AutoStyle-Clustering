require 'prime'

WORDS = [
  [ 3, 'Pling' ],
  [ 5, 'Plang' ],
  [ 7, 'Plong' ]
]

class Raindrops
  def self.convert(num)
    factors = num.prime_division.flatten.sort

    ''.tap do |str|
      WORDS.each { |n,w| str << w if factors.include?(n) }
      str << num.to_s if str.length == 0
    end
  end
end
