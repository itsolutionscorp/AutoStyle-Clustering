require 'prime'

class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def initialize(number)
    @number = number
  end

  def self.convert(number)
    new(number).convert
  end

  def convert
    sound = prime_divisors.map { |prime| SOUNDS[prime] }.join
    sound.empty? ? @number.to_s : sound
  end

  private

  def prime_divisors
    SOUNDS.keys & Prime.prime_division(@number).flatten
  end
end
