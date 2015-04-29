module Raindrops
  def self.convert drops
    speak = ''

    speak << 'Pling' if drops % 3 == 0
    speak << 'Plang' if drops % 5 == 0
    speak << 'Plong' if drops % 7 == 0

    speak.empty? ? drops.to_s : speak
  end
end
