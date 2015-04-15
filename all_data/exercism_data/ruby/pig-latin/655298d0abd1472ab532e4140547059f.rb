class PigLatin
  CONSONANT_LETTERS = %w(b c d f g h j k l m n p q r s t v w x y z)
  CONSONANT_DIGRAPHS = %w(ch ng sh th ck wh and qu)

  def initialize(word)
    @word = word
  end

  def translate
    case
    when start_with_vowel?
      "#{@word}ay"
    else
      w = consonant_digraphs
      "#{@word[w.length..-1]}#{w}ay"
    end
  end

  def self.translate(text)
    text.split.map { |word| new(word).translate }.join(' ')
  end

  private

  def start_with_vowel?
    @word.match(/^([aeiou]|y[^aeiou]|xr)/)
  end

  def consonant_digraphs
    @word.scan(/^([^aeiou]?qu|[^aeiou]+)/).flatten.first
  end
end
