module Raindrops
  def self.convert number
    sound = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }.select {|prime, _|
      number % prime == 0
    }.map(&:last)

    sound.empty? ? number.to_s : sound.join
  end
end
