class Anagram
  def initialize(original_word)
    @original_word = original_word 
  end

  def match(word_array)
    word_array.select do |word|
      letters_of_original_word_match?(word) && original_word_is_not?(word)
    end
  end

  private

  def letters_of_original_word_match?(word)
    Anagram.letter_count(word) == Anagram.letter_count(@original_word) 
  end

  def original_word_is_not?(word)
    word.downcase != @original_word.downcase
  end

  def self.letter_count(word)
    word.scan(/\w/).each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end
end
