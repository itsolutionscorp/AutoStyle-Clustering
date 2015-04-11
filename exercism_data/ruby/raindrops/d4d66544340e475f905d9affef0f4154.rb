require "prime"

class Raindrops

  def convert number
    factors = Raindrops.factors(number)
    sounds = factors.map{|factor|FactorSounds[factor]}.compact
    if sounds.empty?
      number.to_s
    else
      sounds.join
    end
  end

  private

  def self.factors number
    Prime.instance.prime_division(number).map(&:first)
  end

  FactorSounds = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong",
  }
end
