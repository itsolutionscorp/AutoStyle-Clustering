module AnagramExtensions
  refine String do
    def anagram?(word)
      Set.new word.split('') == Set.new self.split('') 
    end    
  end
end

class Anagram
  using AnagramExtensions
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select {|x| @word.anagram?(x)}
  end
end
