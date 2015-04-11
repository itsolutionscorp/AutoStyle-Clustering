require 'prime'

class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    speak_primes = SOUNDS.keys & Prime.prime_division(num).flatten
    speak = speak_primes.map { |prime| SOUNDS[prime] }.join
    speak.empty? ? num.to_s : speak
  end
end
