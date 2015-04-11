class Anagram

  def initialize word
    @source_word = word
  end

  def match list
    list.select { |word| word.is_anagram_of? @source_word }
  end

end

class String

  def is_anagram_of? word
    char_list == word.char_list && downcase != word.downcase
  end

  def char_list
    downcase.chars.sort
  end

end
