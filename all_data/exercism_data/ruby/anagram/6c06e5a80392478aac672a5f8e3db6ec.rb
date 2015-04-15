class Word
  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def raw_letters
    text.strip.downcase.chars.sort
  end
end

class Anagram < Word
  def match(words_to_check)
    words_to_check.
       select { |candidate| anagram_of? candidate }
  end

  private 

  def anagram_of? other_word
    raw_letters == Word.new(other_word).raw_letters
  end
end
