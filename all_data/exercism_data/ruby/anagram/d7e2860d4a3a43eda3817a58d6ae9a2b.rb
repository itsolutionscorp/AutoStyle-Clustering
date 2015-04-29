class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(word_list)
    word_list.select { |other_word| @word.anagram_of?(Word.new(other_word)) }
  end
end

class Word
  def initialize(text)
    @text = text
  end

  def anagram_of?(other_word)
    self != other_word && sorted_chars == other_word.sorted_chars
  end

  def text
    @text.downcase
  end

  def ==(other_word)
    text == other_word.text
  end

  protected

  def sorted_chars
    text.split(//).sort
  end
end
