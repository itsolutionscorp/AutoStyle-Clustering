class Anagram

  def initialize(word)
    @initial_word = word
  end

  def match(word_list)
    word_list.select {|word| isAnagram?(word)}
  end

  def normalized_word
    @initial_word.downcase
  end

  def isAnagram?(word)
    word.downcase!
    word != normalized_word and word.chars.sort == normalized_word.chars.sort 
  end

end
