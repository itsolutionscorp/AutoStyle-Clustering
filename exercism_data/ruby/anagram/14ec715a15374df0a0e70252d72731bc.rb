class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(array_of_strings)
    same_size_strings = keep_same_sized_words(array_of_strings)

    same_size_strings.select do |string|
      split_to_letters_and_normalize(string) == split_to_letters_and_normalize(word)
    end

  end

  def keep_same_sized_words(array_of_strings)
    array_of_strings.select { |string| string.length == word.length }
  end

  def split_to_letters_and_normalize(string)
    string.downcase.split(//).sort
  end

end
