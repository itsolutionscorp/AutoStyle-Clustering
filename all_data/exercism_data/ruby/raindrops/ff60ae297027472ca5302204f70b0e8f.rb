require 'prime'

class Raindrops
  def self.convert(the_number)
    raindrop_sentence = ''
    factors =
      Prime.prime_division(the_number)
      .flat_map { |factor, power| [factor] * power }.uniq

    raindrop_sentence += 'Pling' if factors.include?(3)
    raindrop_sentence += 'Plang' if factors.include?(5)
    raindrop_sentence += 'Plong' if factors.include?(7)

    raindrop_sentence.empty? ? the_number.to_s : raindrop_sentence
  end
end
