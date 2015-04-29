class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match (candidates)     
    candidates.select {|w| sort_word(w) == sort_word(@word)}
 end
 
  private

 def sort_word(word)
    word.chars.sort
  end
end
