class Anagram
  attr_accessor :word

  def initialize(word)
    @word=word
  end

  def match(wordarray)
    wordarray.select {|match| match if is_anagram?(match, word) == true}
  end
  
  private

  def is_anagram?(word1, word2)
      return true if word1.downcase.split("").sort == word2.downcase.split("").sort && word1.downcase != word2.downcase
      return false
  end

end
