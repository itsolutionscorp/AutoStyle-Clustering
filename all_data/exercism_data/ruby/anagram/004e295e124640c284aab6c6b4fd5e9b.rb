class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(collection)
    collection.select do |candidate|
       not_same_word(word, candidate) && letters_match(word, candidate)
    end
  end

  private

  def not_same_word(word, candidate)
    word.downcase != candidate.downcase
  end

  def letters_match(original, candidate)
    split_word(original).sort == split_word(candidate).sort
  end

  def split_word(word)
    word.downcase.chars
  end

end
