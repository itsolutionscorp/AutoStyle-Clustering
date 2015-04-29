class Anagram
  def initialize word
    @source_word = word.to_s.downcase
  end

  def match words
    words.select { |word| anagram?(word) }
  end

  private
  
  def anagram? word
    word = word.downcase

    word != @source_word && char_sort(@source_word) == char_sort(word)
  end

  def char_sort s
    s.chars.sort
  end
end
