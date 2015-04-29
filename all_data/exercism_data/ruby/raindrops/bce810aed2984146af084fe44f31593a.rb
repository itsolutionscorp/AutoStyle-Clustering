require 'prime'

class Raindrops
  TRANSLATION = {'3' => 'Pling', '5' => 'Plang', '7' => 'Plong'}

  def self.convert(number)
    primes = number.prime_division.map(&:first)

    if primes.include?(3) || primes.include?(5) || primes.include?(7)
      primes.join.gsub(/[#{TRANSLATION.keys.join}]/, TRANSLATION).gsub(/\d+/, '')
    else; return number.to_s; end
  end
end
