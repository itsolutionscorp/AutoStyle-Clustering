class AnagramTester
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |word| Word.new(word).anagram_of?(@word) }
  end
end

class Word
  def initialize(word)
    @word = word.downcase
  end

  def anagram_of?(other)
    alphabetized == other.alphabetized && word != other.word
  end

  protected

  attr_reader :word

  def alphabetized
    @word.chars.sort
  end
end

Anagram = AnagramTester
