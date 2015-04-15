require 'debugger'
class Anagram
  
  def initialize string
    @word = Word(string)
  end
  
  def match possible_anagrams
    possible_anagrams.collect! { |letters| Word(letters) }
    possible_anagrams.find_all do |word|
      word.anagram_of? @word 
    end.collect { |result| result.to_s }
  end
  
  private
  
  def Word string
    Word.new(string)
  end
  
  class Word
    
    def initialize word
      @word = String(word)
    end
    
    def anagram_of? other
      !self.identical_to?(other) && self.letters == other.letters
    end
    
    def letters
      @word.downcase.split(//).sort
    end
    
    def identical_to? other
      self.to_s.downcase == other.to_s.downcase
    end
    
    def to_s
      @word
    end
    
  end
  
end
