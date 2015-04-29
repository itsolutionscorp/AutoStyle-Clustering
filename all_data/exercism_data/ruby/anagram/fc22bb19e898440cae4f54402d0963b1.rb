class Anagram
  def initialize anagram
    @anagram = anagram.downcase
  end
  
  def match words
    anagrams = Array.new
    
    words.each do |word|
      anagrams << word if anagram? word.downcase
    end
    
    anagrams
  end
  
  private
  
  def anagram? word
    @anagram.chars.sort === word.chars.sort && @anagram != word
  end
end
