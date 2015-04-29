require 'prime'

class Raindrops

  @raindrop_speak = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
  }

  def Raindrops.convert(number)
    raindrop_sentence = ''

    @raindrop_speak.each do |prime, raindrop_word|
      if Prime.prime_division(number).flatten.include? prime
        raindrop_sentence << raindrop_word
      end
    end

    if raindrop_sentence.length == 0
      return number.to_s
    else
      return raindrop_sentence
    end
  end

end
