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
    if VOWEL_BEGINNINGS.any? { |x| word.start_with?(x) }
      "#{word}ay"
    elsif CONSONANT_BEGINNINGS.any? { |x| word.match(/\A#{x}/) }
      consonant, rest = $&, $'
      "#{rest}#{consonant}ay"
    else
      raise "Word doesn't begin with known vowel nor consonant!"
    end
  end

  def words
    phrase.split
  end

  attr_reader :phrase
end
