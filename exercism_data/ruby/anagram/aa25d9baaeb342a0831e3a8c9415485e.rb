class Anagram
  attr_reader :word

  def initialize(word)
    @word = Word.new(word)
  end

  def match(array_of_strings)

    array_of_strings.select do |string|
      word.is_anagram_of?(string)
    end
    
  end

end


class Word

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def is_anagram_of?(word)
    split_to_letters_and_normalize(text) == split_to_letters_and_normalize(word)
  end

  def split_to_letters_and_normalize(string)
    string.downcase.split('').sort
  end

end
