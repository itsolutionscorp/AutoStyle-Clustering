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
      return true if word.downcase.split("").sort == word2.downcase.split("").sort && word.downcase != word2.downcase
      return false
  end

end
