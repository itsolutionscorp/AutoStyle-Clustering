class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select {|w| @word.hasAnagram?(w)}
  end
end

class Word
  def initialize(word)
    @word = word
    @sortedWord = Word.sort(word)
  end

  def sameWord?(otherWord)
    otherWord.downcase == @word.downcase
  end

  def hasAnagram?(otherWord)
    not sameWord?(otherWord) and @sortedWord == Word.sort(otherWord)
  end

  def self.sort(word)
    word.downcase.chars.sort.join
  end
end
