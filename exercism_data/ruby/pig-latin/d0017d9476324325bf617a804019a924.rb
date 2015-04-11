class PigLatin
  VOWEL_BEGINNINGS     = %w[a o u e i yt xr]
  CONSONANT_BEGINNINGS = %w[ch qu thr th sch squ b c d f g h j k l m n p q r s t v w x y z]

  def self.translate(phrase)
    new(phrase).translate
  end

  def initialize(phrase)
    @phrase = phrase
  end

  def translate
    words.map { |word| translate_word(word) }.join(" ")
  end

  private

  def translate_word(word)
    case word
    when match_beginning(VOWEL_BEGINNINGS)
      "#{word}ay"
    when match_beginning(CONSONANT_BEGINNINGS)
      beginning, rest = Regexp.last_match, Regexp.last_match.post_match
      "#{rest}#{beginning}ay"
    else
      raise "Word doesn't begin with known vowel nor consonant!"
    end
  end

  def match_beginning(beginnings)
    /\A#{Regexp.union(*beginnings)}/
  end

  def words
    phrase.split
  end

  attr_reader :phrase
end
