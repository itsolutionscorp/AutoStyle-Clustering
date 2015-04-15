class Word
  def initialize(word)
    @word = word
  end

  def matches?(other)
    self.normalized_word != other.normalized_word &&
      self.letters == other.letters
  end

  def normalized_word
    @normalized_word ||= @word.downcase
  end

  def letters
    @letters ||= normalized_word.chars.sort
  end
end

class Anagram
  attr_reader :word
  
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |candidate|
      normalized_letters.matches? Word.new(candidate)
    }
  end

  private

  def normalized_letters
    @normalized_letters ||= Word.new word
  end
end
