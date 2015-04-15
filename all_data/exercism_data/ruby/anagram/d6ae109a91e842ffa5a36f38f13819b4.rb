class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |word| @word.anagram? Word.new(word) }
  end
end

class Word
  attr_reader :source

  def initialize(source)
    @source = source
  end

  def anagram?(word)
    !duplicate?(word) && normalized == word.normalized
  end

  def normalized
    source.downcase.chars.sort
  end

  def duplicate?(word)
    downcased == word.downcased
  end

  def downcased
    source.downcase
  end
end
