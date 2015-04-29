class Anagram
  
  def initialize string
    @word = Word(string)
  end
  
  def match possible_anagrams
    possible_anagrams.find_all do |anagram|
      Word(anagram).anagram_of? @word 
    end
  end
  
  private
  
  def Word string
    Word.new(string)
  end
  
  class Word
    
    attr_reader :letters
    
    def initialize word
      @letters = word.downcase.split(//)
    end
    
    def anagram_of? other
      !self.identical_to?(other) && same_letters_as?(other)
    end
    
    def same_letters_as? other
      self.letters.sort == other.letters.sort
    end
    
    def identical_to? other
      self.letters == other.letters
    end
    
    def to_s
      @word
    end
    
  end
  
end
