class Anagram
  def initialize(match_word)
    @match_word = Word.new(match_word)
  end
  
  def match(words)
    words.select do |word|
      @match_word.anagram?(Word.new(word))
    end
  end
end

class Word
  attr_reader :word
  
  def initialize(word)
    @word = word.downcase
  end
  
  def anagram?(other)
    self != other && self.letters == other.letters
  end
  
  def ==(other)
    self.word == other.word
  end
  
  def letters
    word.chars.sort    
  end
end
