class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select{|candidate| anagram?(candidate)}
  end

  private

  def anagram?(candidate)
    matcher = AnagramMatcher.new(word(@subject), word(candidate))
    matcher.anagrams?
  end

  def word(word_content)
    Word.new(word_content.downcase)
  end
end

class AnagramMatcher
  def initialize(word_1, word_2)
    @word_1 = word_1
    @word_2 = word_2
  end

  def anagrams?
    same_chars? && !identical?
  end

  private

  def same_chars?
    @word_1.same_chars?(@word_2)
  end

  def identical?
    @word_1.identical?(@word_2)
  end
end

class Word
  def initialize(content)
    @content = content
  end

  def sorted_chars
    @content.chars.sort
  end

  def ==(word)
    @content == word.to_s
  end

  def to_s
    @content.to_s
  end

  def identical?(word)
    word == self
  end

  def same_chars?(word)
    sorted_chars == word.sorted_chars
  end
end
