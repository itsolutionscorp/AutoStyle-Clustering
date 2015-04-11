class Anagram

  def initialize(word)
    @word = word
  end   

  def match(anagrams)    
    anagrams.select { |anagram| matches_anagram?(anagram) }
  end

  private

  def sort_word(word)
    word.downcase.chars.sort
  end 

  def matches_anagram?(anagram)
    anagram?(anagram) && not_same_word?(anagram)
  end

  def anagram?(anagram)
    sort_word(anagram) == sort_word(@word)
  end

  def not_same_word?(anagram)
    anagram.downcase != @word.downcase
  end 

end
