class Anagram
  
  def initialize(anagram)
    @anagram = anagram.downcase
    @chars = @anagram.chars.sort
  end
  
  def match(words)
    words.select { |word| anagram? word.downcase }
  end
  
  private
  
  def anagram?(word)
    @anagram != word && @chars == word.chars.sort
  end
  
end
