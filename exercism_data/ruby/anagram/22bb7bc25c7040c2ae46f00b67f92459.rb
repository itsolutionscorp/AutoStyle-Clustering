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

    word != @source_word && sorted_chars(@source_word) == sorted_chars(word)
  end

  def sorted_chars s
    s.chars.sort
  end
end
