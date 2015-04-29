class PigLatin
  def self.translate(phrase)
    phrase.split(' ').map do |word|
      PigLatin.new(word).translate
    end.join(' ')
  end

  def initialize(word)
    @word = word.downcase.gsub(/[^a-z]/,'')
  end

  def translate
    if it_starts_with_vowel_or_vowel_sound_y_or_x
      word + "ay"
    else it_starts_with_qu_or_consonants
      remainder + start + "ay"
    end
  end

private
  attr_reader :word, :start, :remainder

  def it_starts_with_vowel_or_vowel_sound_y_or_x
    return true if %w[a e i o u].include? word.chars[0]
    word.match /\A(y[^aeiou]|xr)/
  end

  def it_starts_with_qu_or_consonants
    word.match /\A(s?qu|[^aeiou]+)(.*)/
    @start = $1
    @remainder = $2
  end
end
