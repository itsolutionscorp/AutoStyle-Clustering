class Anagram
  def initialize word
    @word = word.downcase
  end

  def word_array
    @array ||= @word.chars.sort
  end
  
  def anagram? word
    word_array == word.downcase.chars.sort
  end

  def different_word? word
    word.downcase != @word
  end

  def match words
    words.select do |item|
      anagram?(item) && different_word?(item)
    end
  end
end
