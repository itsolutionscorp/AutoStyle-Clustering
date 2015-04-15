class NormalizedLetters
  def initialize(word)
    @word = word
  end

  def matches?(other_normalized_letters)
    normalized_word != other_normalized_letters.normalized_word &&
      letters == other_normalized_letters.letters
  end

  def normalized_word
    @normalized_word ||= @word.downcase
  end

  def letters
    @letters ||= normalized_word.chars.sort
  end

  def to_s
    @letters.to_s
  end
end

class Anagram
  attr_reader :word
  
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |candidate|
      normalized_letters.matches? NormalizedLetters.new candidate
    }
  end

  private

  def normalized_letters
    @normalized_letters ||= NormalizedLetters.new word
  end
end
