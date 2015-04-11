require 'prime'

class Raindrops
  SPEAK = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    speak_primes = SPEAK.keys & Prime.prime_division(num).flatten
    speak = speak_primes.map { |prime| SPEAK[prime] }.join
    speak.empty? ? num.to_s : speak
  end
end
