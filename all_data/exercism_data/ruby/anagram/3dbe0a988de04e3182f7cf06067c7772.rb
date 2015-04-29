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

  def sorted_letters
    @sorted_letters ||= @word.split("").sort
  end

  def anagram_of?(word)
    self.sorted_letters == word.sorted_letters
  end
end
