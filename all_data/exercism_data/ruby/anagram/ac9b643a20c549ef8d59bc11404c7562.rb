class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select{|word| Word.new(word).letters == @word.letters}
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def letters
    @word.split("").sort
  end
end
