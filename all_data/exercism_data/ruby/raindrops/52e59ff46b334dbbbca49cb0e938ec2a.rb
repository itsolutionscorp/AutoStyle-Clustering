require 'prime'

class Raindrops
  @@sounds = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    sounds = prime_factors(number).map { |number| sound(number) }.compact
    sounds.empty? ? number.to_s : sounds.join
  end

  def self.prime_factors(number)
    Prime.prime_division(number).map { |factor, power| factor }
  end
  private_class_method :prime_factors

  def self.sound(number)
    @@sounds[number]
  end
  private_class_method :sound
end
