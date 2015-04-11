require "prime"

class Raindrops
  def convert number
    sounds = factor_sounds(number)
    if sounds.empty?
      number.to_s
    else
      sounds.join
    end
  end

  private

  def factor_sounds(number)
    factors(number).map(&:sound).compact
  end

  def factors(number)
    Prime.instance.prime_division(number).map(&:first).map{|n| Factor.new(n)}
  end

  class Factor
    def initialize value
      @value = value
    end

    def sound
      Sounds[@value]
    end

    Sounds = {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
    }
  end
end
