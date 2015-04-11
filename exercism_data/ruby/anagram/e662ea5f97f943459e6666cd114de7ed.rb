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

  def anagram_identity
    @anagram_identity ||= count_letters
  end

private
  attr_reader :text

  def count_letters
    letter_counts = Hash.new(0)
    text.downcase.scan(/\w/) do |letter|
      letter_counts[letter] += 1
    end
    letter_counts
  end
end
