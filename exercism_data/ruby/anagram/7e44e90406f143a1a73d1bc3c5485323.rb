class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(word_list)
    word_list.select do |member|
      @word.anagram_of? Word.new(member)
    end
  end
end

class Word
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def anagram_of?(other_word)
    self.letters == other_word.letters && self != other_word
  end

  def letters
    word.chars.sort
  end

  private
  def == (other_word)
    self.word == other_word.word
  end
end
