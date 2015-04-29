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
  def initialize(word)
    @word = word
  end

  def anagram_of?(other_word)
    self.characters == other_word.characters && self != other_word
  end

  def == (other_word)
    self.normalized == other_word.normalized
  end

  def normalized
    @word.downcase
  end

  def characters
    normalized.chars.sort
  end
end
