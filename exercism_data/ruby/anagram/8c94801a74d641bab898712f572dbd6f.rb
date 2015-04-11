class Anagram
  def initialize(target_word)
    @target_word = Word.new(target_word)
  end

  def match(candidate_words)
    candidate_words.select do |candidate|
      target_word.is_anagram_of?(Word.new(candidate))
    end
  end

private
  attr_reader :target_word
end

class Word
  def initialize(text)
    @text = text
  end

  def is_anagram_of?(other_word)
    anagram_identity == other_word.anagram_identity
  end

protected
  def anagram_identity
    @anagram_identity ||= text.downcase.chars.sort
  end

private
  attr_reader :text
end
