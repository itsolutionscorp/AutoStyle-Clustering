class PigLatin
  def self.translate phrase
    phrase.split.map do |word|
      new(word).translate
    end.join ' '
  end

  def initialize word
    @word = word
  end

  def translate
    if start_with_vowel?
      word + 'ay'
    else
      consonant, remainder = match_consonant
      remainder + consonant + 'ay'
    end
  end

  private
  def start_with_vowel?
    word.match /\A[aeiou]|yt|xr/
  end

  def match_consonant
    word.scan(/\A([^aeiou]?qu|[^aeiou]+)(.+)/).first
  end

  attr_accessor :word
end
