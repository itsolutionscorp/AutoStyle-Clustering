class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select{|word| Word.new(word).anagram_of?(@word)}
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def letters
    @letters ||= @word.chars
  end

  def anagram_of?(word)
    letters.sort == word.letters.sort
  end
end
