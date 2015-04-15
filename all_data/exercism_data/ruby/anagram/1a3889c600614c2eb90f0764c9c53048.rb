class Word
  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def anagram_of? other_word
    sorted_letters == self.class.new(other_word).sorted_letters
  end

  def sorted_letters
    text.strip.downcase.chars.sort
  end
end

class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words_to_check)
    words_to_check.
       select { |candidate| @word.anagram_of? candidate }
  end
end
