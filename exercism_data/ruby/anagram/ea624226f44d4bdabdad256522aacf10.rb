class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(list)
    list.select { |item| Word.new(item).anagram?(word) }
  end

  private

  attr_reader :word
end

class Word
  def initialize(word)
    @word = word.upcase
  end

  def anagram?(other_word)
    return false if other_word == self

    sorted_chars == other_word.sorted_chars
  end

  def ==(other)
    word == other.word
  end

  protected

  attr_reader :word

  def sorted_chars
    word.chars.sort
  end
end
