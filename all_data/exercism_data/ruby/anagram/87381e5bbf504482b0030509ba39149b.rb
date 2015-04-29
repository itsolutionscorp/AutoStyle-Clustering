class Anagram

  def initialize word

    @word = word.downcase
    @sorted = @word.chars.sort
    
  end

  def match words
    words.each_with_object([]) do |w, matches| 
      matches<< w if anagram?(w.downcase())
    end
  
    
  end

  def anagram? w

    w.size == @word.size && w != @word && @sorted == w.downcase.chars.sort

  end
end
