class Anagram
  def initialize(word)
    @word = word
    @compare = sort_chars(@word)
  end

  def match(words)
    words.find_all { |word| is_anagram?(word, @compare) && !identical?(word, @word) }
  end

  private
  
  def is_anagram?(word1, word2)
    sort_chars(word1) == word2
  end
 
  def identical?(word1, word2)
    word1.downcase == word2.downcase
  end

  def sort_chars(string)
    string.downcase.chars.sort.join
  end
end
