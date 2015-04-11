class Anagram
  attr_reader :word

  def initialize(word)
    @word=word
  end

  def match(wordarray)
    wordarray.select {|match| is_anagram?(match)}
  end

  private

  def is_anagram?(word2)
      unless is_same_word?(word,word2) 
        return true if array_of_chars(word).sort == array_of_chars(word2).sort 
      end
      false
  end

  def array_of_chars(word)
    word.downcase.split("")
  end

  def is_same_word?(word1,word2)
    return true if word1.downcase == word2.downcase
    false
  end

end
