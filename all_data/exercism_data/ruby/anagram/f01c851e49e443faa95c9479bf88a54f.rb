class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end


  def match(array_of_strings)
    same_size_strings = filter_diff_sized_words(array_of_strings)

    same_size_strings.select do |string|
      word.is_anagram_of?(string)
    end
  end


  def filter_diff_sized_words(array_of_strings)
    array_of_strings.select { |string| word.same_size_with?(string) }
  end

end


class Word

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def same_size_with?(word)
    text.length == word.length
  end

  def is_anagram_of?(word)
    split_to_letters_and_normalize(text) == split_to_letters_and_normalize(word)
  end

  def split_to_letters_and_normalize(string)
    string.downcase.split('').sort
  end

end
