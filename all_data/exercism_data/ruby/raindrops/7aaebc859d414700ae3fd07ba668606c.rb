require 'prime'

class Raindrops
  def self.convert(the_number)
    raindrop_sentence = ""
    factors = Prime.prime_division(the_number).flat_map { |factor, power| [factor] * power }.uniq

    if factors.include?(3)
      raindrop_sentence += 'Pling'
    end

    if factors.include?(5)
      raindrop_sentence += 'Plang'
    end

    if factors.include?(7)
      raindrop_sentence += 'Plong'
    end

    if !raindrop_sentence.empty?
      raindrop_sentence
    else
      the_number.to_s
    end
  end
end
