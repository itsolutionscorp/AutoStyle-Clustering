class Anagram
  def initialize(word)
    @word = Word.new(word.downcase)
  end

  def match(words)
    words.select { |word| @word.anagram? Word.new(word) }
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def anagram?(word)
    @word != word.to_s && chars == word.chars
  end

  def chars
    @word.chars.sort
  end

  def to_s
    @word
  end
end
