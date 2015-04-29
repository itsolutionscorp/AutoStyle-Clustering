class PigLatin
  def self.translate(words)
    words.split(" ").map do |word|
      PigLatin.new(word).translate
    end.join(" ")
  end

  def initialize(word)
    @word = word
  end

  def translate
    (word + 'ay') if start_with_vowel_sound?
    add_on_letters, remaining_letters = parse_word
    remaining_letters + add_on_letters + 'ay'
  end

  private

  attr_reader :word

  def start_with_vowel_sound?
    word.match(/\A([aeiou] | yt | xr)/)
  end

  def parse_word
    word.scan(/\A([^aeiou]?qu | [^aeiou]+)(.*)/).first
  end
end
