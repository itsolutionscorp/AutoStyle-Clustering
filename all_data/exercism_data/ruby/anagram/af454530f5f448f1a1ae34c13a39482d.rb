class Anagram

  def initialize word

    @word = word.downcase
    @sorted = @word.chars.sort
    
  end

  def match words
    words.select{|w| anagram?(w.downcase()) }
    
  end

  def anagram? w

    w.size == @word.size && w != @word && @sorted == w.chars.sort

  end
end
