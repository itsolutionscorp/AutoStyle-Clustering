class Anagram
  def initialize(original_word)
    @original_word = original_word 
  end

  def match(words)
    words.select do |word|
      letters_of_original_word_match?(word) && original_word_is_not?(word)
    end
  end

  private

  def original_letters_frequency
    Anagram.letter_count(@original_word)
  end

  def letters_of_original_word_match?(word)
    Anagram.letter_count(word) == original_letters_frequency
  end

  def original_word_is_not?(word)
    word.downcase != @original_word.downcase
  end

  def self.letter_count(word)
    Anagram.word_to_letters(word).each_with_object(Hash.new(0)) do |word, frequencies|
      frequencies[word.downcase] += 1
    end
  end

  def self.word_to_letters(word)
    word.scan(/\w/)
  end

end
